package demo.acs.logic;

import demo.acs.data.UserConverter;
import demo.acs.data.UserEntity;
import demo.acs.data.UserId;
import demo.acs.data.UserRole;
import demo.acs.rest.boudanries.UserBoundary;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserImplementation implements UserService {
	UserConverter userConverter;	
	Map<UserId, UserEntity> userDatabase;
	private String domain;
	
	// injection of value from the spring boot configuration
	@Value("${spring.application.name:demo}")
	public void setProjectName(String domain) {
		this.domain = domain;
	}
	
	@Override
	public Map<String, Object> getProjectName() {
		return Collections.singletonMap("domain", domain);
	}
	
	@Autowired
	public UserImplementation(UserConverter converter) {
		this.userConverter = converter;
	}
	
	@PostConstruct
	public void init() {
		// since this class is a singleton, we generate a thread safe collection
		this.userDatabase = Collections.synchronizedMap(new TreeMap<>());
	}

	@Override
	public UserBoundary createUser(UserBoundary boundry) {
		if(boundry.getAvatar() == null)
			boundry.setAvatar(""); 
       
		if(boundry.getRole()==null)
			throw new InsafitiontInputExeption("Need role to create new user");
		
		UserId userid = boundry.getUserId();
        if(userid.getEmail() == null)
        	throw new InsafitiontInputExeption("Need email to create new user");
        if(userid.getEmail().isEmpty())
        	throw new InsafitiontInputExeption("Need email to create new user");
        
        userid.setDomain(this.domain);
        boundry.setUserId(userid);
        
        if(boundry.getUsername() == null)
        	boundry.setUsername("");
        
        
		UserEntity entity = this.userConverter.toEntity(boundry);
		this.userDatabase.put(entity.getUserId(), entity);
		return boundry;
	}

	@Override
	public UserBoundary login(String userDomain, String userEmail) {
		UserId userId = new UserId();
		userId.setDomain(userDomain);
		userId.setEmail(userEmail);
		UserEntity existing = this.userDatabase.get(userId);
		if (existing != null) {
			return this.userConverter.fromEntity(existing);
		}else {
			throw new UserNotFoundException("could not find object by userDomain: "
	                                    	+ userDomain + " userEmail: " + userEmail);
		}
	}

	@Override
	public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update) {
		UserId userId = new UserId();
		userId.setDomain(userDomain);
		userId.setEmail(userEmail);
				
		UserEntity existing = this.userDatabase.get(userId);
		if (existing == null) {
			throw new UserNotFoundException("could not find object by id: " + userId);
		}
		return this.userConverter.fromEntity(existing);
	}

	@Override
	public List<UserBoundary> getAllUsers(String adminDomain, String adminEmail) {
		return this.userDatabase // Map<String, DummyEntity>
						.values()           // Collection<DummyEntity>
						.stream()		    // Stream<DummyEntity>				
						.map(this.userConverter::fromEntity)	// Stream<DummyBoundaries>		
						.collect(Collectors.toList()); // List<DummyBoundaries>
	}

	@Override
	public void deleteAllUsers(String adminDomain, String adminEma) {
		this.userDatabase.clear();		
	}

}
