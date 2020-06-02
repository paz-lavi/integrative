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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import acs.dal.ElementDao;
import acs.dal.UserDao;
import acs.data.CreatedBy;
import acs.data.CreatedByConverter;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.Location;
import acs.data.UserEntity;
import acs.data.UserId;
import acs.data.UserRole;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.ElementIdBoundary;


@Service
public class ElementServiceImplementationDB implements EnhancedElementService{
	private String projectName;
	private ElementDao elementDao;
	private UserDao userDao;
	private ElementConverter converter;
	private CreatedByConverter createdByConverter;
	public static String VALID_EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	public static double defoultLongitude;
	public static double defoultLatitude;
	
	@Autowired
	public ElementServiceImplementationDB(ElementDao elementDao, ElementConverter converter,
			UserDao userDao, CreatedByConverter createdByConverter) {
		this.elementDao = elementDao;
		this.converter = converter;
		this.userDao = userDao;
		this.createdByConverter = createdByConverter;
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
		
		//check input data. if it's incorrect then throw exception.
		checkInputData(userDomain, userEmail);
		
		//get user id if user in DB 
		UserEntity user = checkIfUserInDB_returnUser(userDomain, userEmail);		
			
		//if user is manager then return all elements active and not active
		if(user.getRole().equals(UserRole.MANAGER)) {
			// return all elements
			return this.elementDao.findAll(
					PageRequest.of(page,size, Direction.DESC, "type")) // Page<ElementEntity>
					.getContent() // List<ElementEntity>
					.stream() // Stream<ElementEntity>
					.map(this.converter::fromEntity) // Stream<ElementBoundary>
					.collect(Collectors.toList()); // List<ElementBoundary>
					
		//if user is player then return all elements active and not active
		} else if(user.getRole().equals(UserRole.PLAYER)){
			// return all elements
			return this.elementDao.findAllByisActive(true, 
					PageRequest.of(page, size, Direction.DESC, "type")) // Page<ElementEntity>
					.stream() // Stream<ElementEntity>
					.map(this.converter::fromEntity) // Stream<ElementBoundary>
					.collect(Collectors.toList()); // List<ElementBoundary>	
		
		//if user is not manager or player then throw exception
		} else
			throw new RuntimeException("This user has no permission for setrieval or search element" + user.toString());	
	}
	
	
	@Override
	@Transactional
	public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element) {
		//check input data. if it's incorrect then throw exception.
				checkInputData(managerDomain, managerEmail);
				
				//get user id if user in DB 
				UserEntity user = checkIfUserInDB_returnUser(managerDomain, managerEmail);
				
				if(!user.getRole().equals(UserRole.MANAGER))
					throw new RuntimeException("This user has no permissions for creating element" + user.toString());      
					
				//update element's attributes by 
				if (element.getType() == null)
					element.setType("None");	
		        if (element.getName() == null)
					element.setName("");
				if (element.getElementAttributes() == null)
					element.setElementAttributes(new HashMap<>());
		        if(element.getLocation() == null)
		            element.setLocation(new Location(defoultLatitude, defoultLongitude));
				
		        ElementId id = new ElementId();
				id.setId(UUID.randomUUID().toString());
				id.setDomain(projectName);	
				
				element.setElementId(id);	
				element.setIsActive(true);
		        element.setCreatedTimestamp(new Date());
		        
		        element.setCreatedBy(this.createdByConverter.fromUserIdToCreatedBy(user.getUserId()));

		        //convert ElementBoundary to ElementEntity
				ElementEntity entity = this.converter.toEntity(element);
				
				//return ElementBoundary
				return this.converter.fromEntity(this.elementDao.save(entity));
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

		//check input data. if it's incorrect then throw exception.
		checkInputData(userDomain, userEmail);
		
		//get user id if user in DB 
		UserEntity user = checkIfUserInDB_returnUser(userDomain, userEmail);	
		
		// get element from database
		ElementEntity elementExisting = this.elementDao.findById(new ElementId(elementDomain,elementId))
				.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));
			
		//if user is manager then return all elements active and not active
		if(user.getRole().equals(UserRole.MANAGER))
			return converter.fromEntity(elementExisting);
		
		else if(user.getRole().equals(UserRole.PLAYER) && elementExisting.getActive())
			return converter.fromEntity(elementExisting);
		
		else if(user.getRole().equals(UserRole.PLAYER) && !elementExisting.getActive())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "element not found");
		
		else 
			throw new RuntimeException("This user has no permission for setrieval or search element" + user.toString());	

	}
	@Override
	@Transactional
	public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain,
			String elementID, ElementBoundary update) {
		//check input data. if it's incorrect then throw exception.
		checkInputData(managerDomain, managerEmail);
		
		//get user id if user in DB 
		UserEntity user = checkIfUserInDB_returnUser(managerDomain, managerEmail);
		
		if(!user.getRole().equals(UserRole.MANAGER))
			throw new RuntimeException("This user has no permissions for udatin element" + user.toString());   
		
		//get element from DB
		ElementId id = new ElementId();
		id.setDomain(elementDomain);
		id.setId(elementID);	
		ElementEntity existing = this.elementDao.findById(id)
		.orElseThrow(()->new RuntimeException("could not find object by id: " + id));
		
		// update element's attributes
		if (update.getName() != null) 
			existing.setName(update.getName());
		if (update.getType() != null) 
			existing.setType(update.getType());
		if (update.isActive() == false) 
			existing.setActive(true);
        if (update.getLocation() != null) 
			existing.setLocation(update.getLocation());
		if (update.getElementAttributes() != null)
			existing.setElementAttributes(update.getElementAttributes());
		
        // save updated element to DB
		return this.converter.fromEntity(this.elementDao.save(existing));
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
		childExisting.setParent(parentExisting);
		this.elementDao.save(parentExisting);
		this.elementDao.save(childExisting);
	

			
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllChildrenOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId, int size, int page) {
		
		//check input data. if it's incorrect then throw exception.
		checkInputData(userDomain, userEmail);
		
		//get user id if user in DB 
		UserEntity user = checkIfUserInDB_returnUser(userDomain, userEmail);		
		
		
		//if user is manager then return all elements active and not active
		if(user.getRole().equals(UserRole.MANAGER)) {
			// get entity elements from database
			ElementEntity elementExisting = this.elementDao.findById(new ElementId(elementDomain,elementId))
					.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));	
			
			return this.elementDao.findAllByparent_elementId(elementExisting.getElementId(), 
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "elementId")).stream()
					.map(this.converter::fromEntity)
					.collect(Collectors.toSet());

		
			//if user is player then return all elements active and not active
		} else if(user.getRole().equals(UserRole.PLAYER)){
			// get entity objects from database
			ElementEntity elementExisting = this.elementDao.findAllByElementIdAndActive(new ElementId(elementDomain,elementId), true)
					.orElseThrow(()->new RuntimeException("could not find object by id: " + elementId));
			
			// return only active elements 
			return this.elementDao.findAllByparent_elementIdAndActive(elementExisting.getElementId(),
					true,
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "elementId")).stream()
					.map(this.converter::fromEntity)
					.collect(Collectors.toSet());

		//if user is not manager or player then throw exception
		} else
			throw new RuntimeException("This user has no permission for setrieval or search element" + user.toString());	
	}
	

	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllParentsOfElement(String userDomain, String userEmail, String elementDomain,
			String elementId, int size, int page) {
		
		//check input data. if it's incorrect then throw exception.
		checkInputData(userDomain, userEmail);
		
		//get user id if user in DB 
		UserEntity user = checkIfUserInDB_returnUser(userDomain, userEmail);		
			
		//if user is manager then return all elements active and not active
		if(user.getRole().equals(UserRole.MANAGER)) {
			// get element from database
			return this.elementDao.findParentByElementId(new ElementId(elementDomain,elementId),
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "elementId"))
					.stream()
					.map(this.converter::fromEntity)
					.collect(Collectors.toSet());
		
					
		//if user is player then return all elements active and not active
		} else if(user.getRole().equals(UserRole.PLAYER)){
			// get element from database
			return this.elementDao.findParentByElementIdAndParent_active(new ElementId(elementDomain,elementId),
					true,
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "elementId"))
					.stream()
					.map(this.converter::fromEntity)
					.collect(Collectors.toSet());		
		
		//if user is not manager or player then throw exception
		} else
			throw new RuntimeException("This user has no permission for setrieval or search element" + user.toString());	
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
	
	
	@Transactional
	 public List<ElementEntity> saveAllStudent(List<ElementEntity> studentList) {
	 List<ElementEntity> response = (List<ElementEntity>) elementDao.saveAll(studentList);
	 return response;
	 }
	
	
	
	@Override
	public boolean checkIfEmailIsValid(String email) {
		if (Pattern.matches(VALID_EMAIL_PATTERN, email.trim())) {
			return true;
		}
		return false;
	}
	
	
	
	
	@Override
	public void checkInputData(String userDomain, String userEmail) {
		//check input data
        if(userDomain.equals(null) || userDomain.isEmpty())
        	throw new RuntimeException("Domain is null or empty");        
        if(userEmail.equals(null))
        	throw new RuntimeException("Email is null");
        if(!checkIfEmailIsValid(userEmail))
        	throw new RuntimeException("Email is incorrect");
	}
	
	
	@Override
	public UserEntity checkIfUserInDB_returnUser(String userDomain,
			String userEmail) {
		//create user id 
		UserId userId = new UserId();
		userId.setDomain(userDomain);
		userId.setEmail(userEmail);		
		//get user from DB
		Optional<UserEntity>existing = userDao.findById(userId);		
		//check if user was DB
		if (!existing.isPresent()) 
			throw new UserNotFoundException("Could not find user by id: " + userId);
		UserEntity user = existing.get();		

		return user;
	}


}
