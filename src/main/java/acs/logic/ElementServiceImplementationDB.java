package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.Location;
import acs.data.UserId;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.ElementIdBoundary;


@Service
public class ElementServiceImplementationDB implements EnhancedElementService{
	private String projectName;
	private ElementDao elementDao;
	private ElementConverter converter; 
	
	@Autowired
	public ElementServiceImplementationDB(ElementDao elementDao, ElementConverter converter) {
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
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAll(String userDomain,String  userEmail,int size, int page) {
		return this.elementDao.findAll(
				PageRequest.of(page,size, Direction.DESC, "type")) // Page<ElementEntity>
				.getContent() // List<ElementEntity>
				.stream() // Stream<ElementEntity>
				.map(this.converter::fromEntity) // Stream<ElementBoundary>
				.collect(Collectors.toList()); // List<ElementBoundary>
	}
	@Override
	@Transactional
	public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element) {
		ElementId id = new ElementId();
		id.setId(UUID.randomUUID().toString());
		id.setElementDomain(projectName);
		
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
		element.setIsActive(true);
        element.setCreatedTimeStamp(new Date());
        
        if(managerDomain.equals(null))
        	throw new RuntimeException("Need to have a managerDomain");
        
        if(managerEmail.equals(null))
        	throw new RuntimeException("Need to have a managerEmail");
        
        element.setCreatedBy(userId);
        
        if(element.getLocation() == null)
        element.setLocation(new Location(0,0));
        
		ElementEntity entity = this.converter.toEntity(element);
		entity.setCreatedTimeStamp(new Date());
		
		return this.converter
			.fromEntity(
				this.elementDao.save(entity));
	}
	
	
	@Override
	@Transactional
	public void deleteAllElements(String adminDomain, String adminEmail) {
		this.elementDao.deleteAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain,
			String elementId) throws Exception {
		
		Optional<ElementEntity> existing = this.elementDao.findById(new ElementId(elementDomain,elementId));
		
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
	@Transactional
	public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID,
			ElementBoundary update) {
		
		ElementId id = new ElementId();
		id.setElementDomain(elementDomain);
		id.setId(elementID);
		
		ElementEntity existing = this.elementDao.findById(new ElementId(elementDomain,elementID))
		.orElseThrow(()->new RuntimeException("could not find object by id: " + id));
		
		if (update.getName() != null) {
			existing.setName(update.getName());
		}
		if (update.getType() != null) {
			existing.setType(update.getType());
		}
		
		if (update.isActive() == false) {
			existing.setActive(true);
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

	@Override
	@Transactional
	public void bind(String managerDomain, String managerEmail, String elementDomain, String elementID,
			ElementIdBoundary update) {
	
		ElementEntity parentExisting = this.elementDao.findById(new ElementId(elementDomain,elementID))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + elementID));
		
		ElementEntity childExisting = this.elementDao.findById(new ElementId(elementDomain,elementID))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + update.getId()));
		
		//Add child
		parentExisting.addResponse(childExisting);
		this.elementDao.save(parentExisting);
	

			
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllChildrensOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId) {
		
		// get entity objects from database
		ElementEntity elementExisting = this.elementDao.findById(new ElementId(elementDomain,elementId))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));
		
		return elementExisting
				.getResponses()
				.stream()
				.map(this.converter::fromEntity)
				.collect(Collectors.toSet());
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllParentsOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId) {
		
		// get entity objects from database
		ElementEntity elementExisting = this.elementDao.findById(new ElementId(elementDomain,elementId))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));
		
		Set<ElementBoundary> rv = new HashSet<>(); 
		
		while(elementExisting.getOrigin()!=null) {
			rv.add(this.converter.fromEntity(elementExisting.getOrigin()));
			elementExisting=elementExisting.getOrigin();
		}
		
		return rv;
	}
}
