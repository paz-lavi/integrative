package acs.logic;

import java.util.ArrayList;
import java.util.Collection;
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
		element.setIsActive(true);
        element.setCreatedTimestamp(new Date());
        
        if(managerDomain.equals(null))
        	throw new RuntimeException("Need to have a managerDomain");
        
        if(managerEmail.equals(null))
        	throw new RuntimeException("Need to have a managerEmail");
        
        element.setCreatedBy(userId);
        
        if(element.getLocation() == null)
        element.setLocation(new Location(0,0));
        
		ElementEntity entity = this.converter.toEntity(element);
		entity.setCreatedTimestamp(new Date());
		
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
		id.setDomain(elementDomain);
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
		
		ElementEntity childExisting = this.elementDao.findById(new ElementId(elementDomain,update.getId()))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + update.getId()));
		
		//Add child
		parentExisting.addChild(childExisting);
		this.elementDao.save(parentExisting);
	

			
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllChildrenOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId, int size, int page) {
	
		ElementId element_Id = new ElementId(elementDomain,elementId);
		
		return this.elementDao.findAllByElementId(element_Id,
				PageRequest.of(page, size, Direction.DESC, "createdTimeStamp"))
				.stream()
				.map(this.converter::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllParentsOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId, int size, int page) {
		
		// get entity objects from database
		ElementEntity elementExisting = this.elementDao.findById(new ElementId(elementDomain,elementId))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));
		
		if (size < 1) {
			throw new RuntimeException("size must be not less than 1"); 
		}
		
		if (page < 0) {
			throw new RuntimeException("page must not be negative");
		}
		
		ElementEntity origin = elementExisting.getParent();
		
		Set<ElementBoundary> rv = new HashSet<>(); 
		
		// page = 0 && size >= 1
		if (origin != null && page == 0) {
			ElementBoundary rvBoundary = this.converter.fromEntity(origin);
			rv.add(rvBoundary);
		}
		return rv;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getElementByType(String type, int size, int page) {
		return this.elementDao
				.findAllByType(type, PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "elementId"))
				.stream()
				.map(this.converter::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<ElementBoundary> getElementByName(String name, int size, int page) {
		return this.elementDao
				.findAllByName(name, PageRequest.of(page, size, Direction.DESC,"createdTimestamp", "elementId"))
				.stream()
				.map(this.converter::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<ElementBoundary> getElementByLocation(double minLat,  double maxLat, double minLng, double maxLng, int size, int page) {
		return this.elementDao
				.findAllByLocation_LatBetweenAndLocation_LngBetween(minLat, maxLat, minLng, maxLng,PageRequest.of(page, size, Direction.DESC,"createdTimestamp", "elementId"))
				.stream()
				.map(this.converter::fromEntity)
				.collect(Collectors.toList());
	}
}
