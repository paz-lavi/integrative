package acs.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.rest.boudanries.UserBoundary;
import acs.dal.UserDao;
import acs.data.UserConverter;
import acs.data.UserEntity;
import acs.data.UserId;

@Service
public class UserServiceImplementationDB extends UserImplementation {
    private UserConverter userConverter;
	private String domain;
	private UserDao userDao;
	public static String VALID_EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	
	// injection of value from the spring boot configuration
	@Value("${spring.application.name:demo}")
	public void setProjectName(String domain) {
		this.domain = domain;
	}
	
	@Autowired
	public UserServiceImplementationDB(UserConverter converter, UserDao userDao) {
		super(converter);
		this.userConverter = converter;
		this.userDao = userDao;
	}

	@Override
	@Transactional
    public UserBoundary createUser(UserBoundary boundary) {
        if (boundary.getRole() == null)
			throw new RuntimeException("Need role to create new user");

        UserId userid = boundary.getUserId();
        if(userid.getEmail() == null)
        	throw new RuntimeException("Need email to create new user");
    
        if(userid.getEmail().isEmpty() || userid.getEmail() == null)
        	throw new RuntimeException("Need email to create new user");
       
        if(!checkIfEmailIsValid(userid.getEmail()))
        	throw new RuntimeException("Email not valid");
        
        userid.setDomain(this.domain);
        boundary.setUserId(userid);

        if (boundary.getUsername() == null || boundary.getUsername().equals(""))
        	throw new RuntimeException("User's name cannot be null or empty");
        
        if (boundary.getAvatar() == null || boundary.getAvatar().equals(""))
        	throw new RuntimeException("User's avatar cannot be null or empty");

        UserEntity entity = this.userConverter.toEntity(boundary);
        return this.userConverter.fromEntity(this.userDao.save(entity));
	}

	@Override
	@Transactional(readOnly = true)
    public UserBoundary login(String userDomain, String userEmail) {
		UserId userId = new UserId();
		userId.setDomain(userDomain);
		userId.setEmail(userEmail);
		Optional<UserEntity>existing = userDao.findById(userId);
		if (existing.isPresent()) {
			return this.userConverter.fromEntity(existing.get());
		}else {
			throw new UserNotFoundException("could not find object by userDomain: "
	                                    	+ userDomain + " userEmail: " + userEmail);
		}
	}

	@Override
	@Transactional
    public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update) {
		UserId userId = new UserId();
		userId.setDomain(userDomain);
		userId.setEmail(userEmail);
		
		Optional<UserEntity>existing = userDao.findById(userId);
		
		if (!existing.isPresent()) {
			throw new UserNotFoundException("could not find object by id: " + userId);
		}
		
		UserEntity updatedUser = existing.get();
		updatedUser.setAvatar(update.getAvatar());
		updatedUser.setRole(update.getRole()); 
		updatedUser.setUsername(update.getUsername());
		
		return this.userConverter.fromEntity(userDao.save(updatedUser));	
	}

	@Override
	@Transactional(readOnly = true)
    public List<UserBoundary> getAllUsers(String adminDomain, String adminEmail) {
		Iterable<UserEntity> all = this.userDao.findAll();
		
		List<UserBoundary> rv = new ArrayList<>(); 
		for (UserEntity entity : all) {
			rv.add(this.userConverter.fromEntity(entity));
		}
		
		return rv;
	}

	@Override
	@Transactional
    public void deleteAllUsers(String adminDomain, String adminEmail) {
		this.userDao.deleteAll();	
	}

	private boolean checkIfEmailIsValid(String email) {
		if (Pattern.matches(VALID_EMAIL_PATTERN, email.trim())) {
			return true;
		}
		return false;
	}
	
}
