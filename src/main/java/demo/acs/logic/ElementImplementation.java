package demo.acs.logic;

import demo.acs.data.ElementConverter;
import demo.acs.data.ElementEntity;
import demo.acs.data.ElementId;
import demo.acs.rest.boudanries.ElementBoundary;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ElementImplementation implements ElementService {
    private ElementConverter elementConverter;
    private Map<ElementId, ElementEntity> elementDatabase;
	@SuppressWarnings("unused")
	private String domain;

	// injection of value from the spring boot configuration
	@Value("${spring.application.name:demo}")
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
		this.elementDatabase = Collections.synchronizedMap(new TreeMap<>());
	}
	@Override
	public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element) {
		
        if (element.getType() == null)
            element.setType("");

        if (element.getName() == null)
			element.setName("");

        element.setActive(false);
        element.setCreatedTimeStamp(new Date());
        if(managerDomain.equals(null))
        	throw new InsafitiontInputExeption("Need to have a managerDomain");
        else
        	element.getCreatedBy().setDomain(managerDomain);
        
        if(managerEmail.equals(null))
        	throw new InsafitiontInputExeption("Need to have a managerEmail");
        else
        	element.getCreatedBy().setEmail(managerEmail);
        
        element.setLocation(null);
        ElementEntity entity = this.elementConverter.toEntity(element);
        this.elementDatabase.put(entity.getElementId(), entity);
        return element;
	}

	@Override
	public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID,
			ElementBoundary update) {
		
		ElementId elementId = new ElementId();
		elementId.setDomain(elementDomain);
		elementId.setId(elementID);
		
		ElementEntity existing = this.elementDatabase.get(elementId);
		if (existing == null) {
			throw new UserNotFoundException("could not find object by id: " + elementId);
		}
		
		update.getCreatedBy().setDomain(managerDomain);
		update.getCreatedBy().setEmail(managerEmail);
	
		return this.elementConverter.fromEntity(existing);
	}

	@Override
	public List<ElementBoundary> getAll(String userDomain, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain,
			String elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllElements(String adminDomain, String adminEmail) {
		// TODO Auto-generated method stub
		
	}


}
