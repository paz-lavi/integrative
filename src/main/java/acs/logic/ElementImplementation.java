package acs.logic;

import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.Location;
import acs.data.UserId;
import acs.rest.boudanries.ElementBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ElementImplementation implements ElementService {
    private ElementConverter elementConverter;
    private Map<ElementId, ElementEntity> elementDatabase;
	private String domain;

	// injection of value from the spring boot configuration
	@Value("${spring.application.name:}")
	public void setProjectName(String domain) {
		this.domain = domain;
	}
	
	@Autowired
	public ElementImplementation(ElementConverter converter) {
		this.elementConverter = converter;
	}

	@PostConstruct
	public void init() {
		// since this class is a singleton, we generate a thread safe collection
		this.elementDatabase = Collections.synchronizedMap(new HashMap<>());
	}
	@Override
	public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element) {
		ElementId id = new ElementId();
		id.setId(UUID.randomUUID().toString());
		id.setDomain(domain);
		
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
        	throw new InsafitiontInputExeption("Need to have a managerDomain");
        
        if(managerEmail.equals(null))
        	throw new InsafitiontInputExeption("Need to have a managerEmail");
        
        element.setCreatedBy(userId);
        
        element.setLocation(new  Location(32.123704, 34.806708));
        
		ElementEntity entity = this.elementConverter.toEntity(element);
		this.elementDatabase.put(entity.getElementId(), entity);
		return this.elementConverter.fromEntity(entity);
	}

	@Override
	public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID,
			ElementBoundary update) {
		
		ElementId id = new ElementId();
		id.setDomain(elementDomain);
		id.setId(elementID);
		
		ElementEntity existing = this.elementDatabase.get(id);
		if (existing == null) {
			throw new UserNotFoundException("could not find object by id: " + id);
		}
		if (update.getName() != null) {
			existing.setName(update.getName());
		}
		if (update.getType() != null) {
			existing.setType(update.getType());
		}
		
		if (update.isActive() != false) {
			existing.setIsActive(true);
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
		
		return this.elementConverter.fromEntity(existing);
	}

	@Override
	public List<ElementBoundary> getAll(String userDomain, String userEmail) {
		return this.elementDatabase // Map<String, ElementEntity>
				.values()           // Collection<ElementEntity>
				.stream()		    // Stream<ElementEntity>				
				.map(this.elementConverter::fromEntity)	// Stream<ElementBoundary>		
				.collect(Collectors.toList()); // List<ElementBoundary>
	}

	@Override
	public ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain,
			String elementId) throws Exception {
		
		ElementId id = new ElementId();
		id.setDomain(elementDomain);
		id.setId(elementId);
		
		ElementEntity existing = this.elementDatabase.get(id);
		if (existing != null) {
			return this.elementConverter
				.fromEntity(
					existing);
		}else {
			throw new Exception("could not find object by id: " + elementId);
		}

	}

	@Override
	public void deleteAllElements(String adminDomain, String adminEmail) {
		this.elementDatabase.clear();
	}


}
