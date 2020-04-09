package main.java.demo.acs.logic;

import main.java.demo.acs.data.UserConverter;
import main.java.demo.acs.data.UserEntity;
import main.java.demo.acs.data.UserId;
import main.java.demo.acs.rest.boudanries.UserBoundry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;



@Service
public class UserImplementation implements UserService {
	UserConverter userConverter;	
	Map<UserId, UserEntity> userDatabase;


	@Override
	public UserBoundry createUser(UserBoundry boundry) {
		UserEntity entity = this.userConverter.toEntity(boundry);
		this.userDatabase.put(entity.getUserId(), entity);
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
