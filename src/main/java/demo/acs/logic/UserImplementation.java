package main.java.demo.acs.logic;

import main.java.demo.acs.data.UserConverter;
import main.java.demo.acs.data.UserEntity;
import main.java.demo.acs.data.UserId;
import main.java.demo.acs.rest.boudanries.UserBoundry;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserImplementation implements UserService {
	UserConverter userConverter;	
	Map<UserId, UserEntity> userDatabase;
	
	
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
	public UserBoundry createUser(UserBoundry boundry) {
		UserEntity entity = this.userConverter.toEntity(boundry);
		System.out.println(entity.toString()); 
		this.userDatabase.put(entity.getUserId(), entity);
		System.out.println(this.userDatabase.get(entity.getUserId()).toString()); 
		return boundry;
	}

	@Override
	public UserBoundry login(String userDomain, String userEmail) {
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
	public UserBoundry updateUser(String userDomain, String userEmail, UserBoundry update) {
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
	public List<UserBoundry> getAllUsers(String adminDomain, String adminEmail) {
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
