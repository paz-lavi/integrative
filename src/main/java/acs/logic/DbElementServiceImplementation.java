package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.Location;
import acs.data.UserId;
import acs.rest.boudanries.ElementBoundary;


@Service
public class DbElementServiceImplementation implements EnhancedElementService{
	private String projectName;
	private ElementDao elementDao;
	private ElementConverter converter; 
	
	@Autowired
	public DbElementServiceImplementation(ElementDao elementDao, ElementConverter converter) {
		this.elementDao = elementDao;
		this.converter = converter;
	}
	
	// injection of value from the spring boot configuration
	@Value("${spring.application.name:demo}")
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAll(String userDomain, String userEmail) {
		// get entity objects from database
		Iterable<ElementEntity> all = this.elementDao
			.findAll();

		List<ElementBoundary> rv = new ArrayList<>(); 
		for (ElementEntity entity : all) {
			// map entities to boundaries
			rv.add(this.converter.fromEntity(entity));
		}
		return rv;
	}

	@Override
	@Transactional//(readOnly = false)
	public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element) {
		ElementId id = new ElementId();
		id.setId(UUID.randomUUID().toString());
		id.setDomain(projectName);
		
		element.setElementId(id);
		
		UserId userId = new UserId();
		userId.setDomain(managerDomain);
		userId.setEmail(managerEmail);
		
		if (element.getType() == null) {
			element.setType("None");
		}
		
        if (element.getName() == null)
			element.setName("");

       
		if (element.getElementAttributes() == null) {
			element.setElementAttributes(new HashMap<>());
		}
		element.setIsActive(false);
        element.setCreatedTimeStamp(new Date());
        
        if(managerDomain.equals(null))
        	throw new RuntimeException("Need to have a managerDomain");
        
        if(managerEmail.equals(null))
        	throw new RuntimeException("Need to have a managerEmail");
        
        element.setCreatedBy(userId);
        
        element.setLocation(new Location(32.123704, 34.806708));
        
		ElementEntity entity = this.converter.toEntity(element);
		entity.setCreatedTimeStamp(new Date());
		
		return this.converter
			.fromEntity(
				this.elementDao.save(entity));
	}
	
	
	@Override
	@Transactional//(readOnly = false)
	public void deleteAllElements(String adminDomain, String adminEmail) {
		this.elementDao.deleteAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain,
			String elementId) throws Exception {
		
		Optional<ElementEntity> existing = this.elementDao.findById(elementId);
		
		if (existing.isPresent()) {
			return this.converter
				.fromEntity(
					existing.get());
			
			// commit transaction 
		}else {
			throw new RuntimeException("could not find object by id: " + elementId);
			// rollback transaction
		}
	}
	
	@Override
	@Transactional//(readOnly = false)
	public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID,
			ElementBoundary update) {
		
		ElementId id = new ElementId();
		id.setDomain(elementDomain);
		id.setId(elementID);
		
		ElementEntity existing = this.elementDao.findById(elementID)
		.orElseThrow(()->new RuntimeException("could not find object by id: " + id));
		
		if (update.getName() != null) {
			existing.setName(update.getName());
		}
		if (update.getType() != null) {
			existing.setType(update.getType());
		}
		
		if (update.isActive() != false) {
			existing.setActive(true);
		}
		
		existing.setCreatedTimeStamp(new Date());
		if (update.getCreatedBy() != null) {
			existing.setCreatedBy(update.getCreatedBy());
		}
		if (update.getLocation() != null) {
			existing.setLocation(update.getLocation());
		}
		
		if (update.getElementAttributes() != null) {
			existing.setElementAttributes(update.getElementAttributes());
		}
		
		return this.converter
				.fromEntity(
					this.elementDao.save(existing));
	}

	// origin<----->response
	@Transactional
	public void connectDummies (String originId, String responseId) {
		ElementEntity origin = this.elementDao.findById(originId)
				.orElseThrow(()->
						new RuntimeException("could not find origin by id:" + originId));
		
		ElementEntity response = this.elementDao.findById(responseId)
				.orElseThrow(()->
						new RuntimeException("could not find response by id:" + responseId));

		origin.addResponse(response);
		this.elementDao.save(origin);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllResponses (String originId) {
		ElementEntity origin = this.elementDao.findById(originId)
				.orElseThrow(()->
						new RuntimeException("could not find origin by id:" + originId));
		
		return origin
			.getResponses()
			.stream()
			.map(this.converter::fromEntity)
			.collect(Collectors.toSet());
	}
}