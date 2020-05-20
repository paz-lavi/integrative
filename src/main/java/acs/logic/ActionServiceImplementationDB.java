package acs.logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import acs.dal.ActionDao;
import acs.dal.ElementDao;
import acs.dal.UserDao;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.data.ActionId;
import acs.data.ActionIdGenerator;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.UserEntity;
import acs.data.UserId;
import acs.data.UserRole;
import acs.rest.boudanries.ActionBoundary;

@Service
public class ActionServiceImplementationDB implements EnhancedActionService{
	private ActionDao actionDao;
	private UserDao userDao;
	private ElementDao elementDao;
	private ActionConverter converter; 
    private String domain;
	public static String VALID_EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@Autowired
	public ActionServiceImplementationDB(ActionDao actionDao, ActionConverter converter,
			UserDao userDao, ElementDao elementDao) {
		this.actionDao = actionDao;
		this.converter = converter;
		this.userDao = userDao;
		this.elementDao = elementDao;
	}

	 // injection of value from the spring boot configuration
  	@Value("${spring.application.name:demo}")
  	public void setProjectName(String domain) {
  		this.domain = domain;
  	}


	@Override
	@Transactional
	public Object InvokeAction(ActionBoundary action) {
		
		UserId userId = action.getInvokedBy();
		//check user id. if it's incorrect then throw exception.
		checkInputData(userId.getDomain(), userId.getEmail());		
		//get user from DB
		Optional<UserEntity> existingUser = userDao.findById(userId);			
		if (!existingUser.isPresent()) 
			throw new RuntimeException("user not in the system: " + userId);	
		UserEntity user = existingUser.get();		
		if (!user.getRole().equals(UserRole.PLAYER))
			throw new RuntimeException("\"This user has no permission: " + userId);
		
		ElementId elementId = action.getElement();
		Optional<ElementEntity> existingElement = elementDao.findById(elementId);	
		if (!existingElement.isPresent()) 
			throw new UserNotFoundException("element not in system: " + userId);
		ElementEntity element = existingElement.get();
		if(!element.getActive())
			throw new UserNotFoundException("element not in system: " + userId);
		
		ActionId aid = new ActionId();
    	aid.setDomain(this.domain);
    	aid.setId(ActionIdGenerator.nextValue());
    	action.setActionId(aid);
    	
		if (action.getType() == null) {
			action.setType("None");
		}
		this.actionDao.save(this.converter.toEntity(action));
        return (Object)action;
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail) {
		// get entity objects from database
		Iterable<ActionEntity> all = this.actionDao
			.findAll();

		List<ActionBoundary> rv = new ArrayList<>(); 
		for (ActionEntity entity : all) {
			// map entities to boundaries
			rv.add(this.converter.fromEntity(entity));
		}
		return rv;
	}
	
	@Override
	@Transactional
	public void deleteAllActions(String adminDomain, String adminEmail) {
		this.actionDao.deleteAll();
		
	}

	@Override
	public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail, int size, int page) {
		return this.actionDao.findAll(
				PageRequest.of(page, size,Direction.DESC,"actionId")).getContent().stream().
				map(this.converter::fromEntity).collect(Collectors.toList());
	}
	
	
	public void checkInputData(String userDomain, String userEmail) {
		//check input data
        if(userDomain.equals(null) || userDomain.isEmpty())
        	throw new RuntimeException("Domain is null or empty");        
        if(userEmail.equals(null))
        	throw new RuntimeException("Email is null");
        if(!checkIfEmailIsValid(userEmail))
        	throw new RuntimeException("Email is incorrect");
	}
	

	public boolean checkIfEmailIsValid(String email) {
		if (Pattern.matches(VALID_EMAIL_PATTERN, email.trim())) {
			return true;
		}
		return false;
	}

}
