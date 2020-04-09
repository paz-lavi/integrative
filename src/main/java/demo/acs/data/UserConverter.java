package main.java.demo.acs.data;

import org.springframework.stereotype.Component;

import main.java.demo.acs.rest.boudanries.UserBoundry;


@Component
public class UserConverter {
	
	public UserBoundry fromEntity(UserEntity entity) {
		UserBoundry rv = new UserBoundry();
		rv.setAvatar(entity.getAvatar());
		rv.setRole(entity.getRole());
		rv.setUserId(entity.getUserId());
		rv.setUsername(entity.getUsername());
		return rv;
	}
	
	public UserEntity toEntity(UserBoundry boundry) {
		UserEntity rv = new UserEntity();
		rv.setAvatar(boundry.getAvatar());
		rv.setRole(boundry.getRole());
		rv.setUserId(boundry.getUserId());
		rv.setUsername(boundry.getUsername());
		return rv;
	}
	
}


