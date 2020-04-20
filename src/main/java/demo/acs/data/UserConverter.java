package demo.acs.data;

import org.springframework.stereotype.Component;

import demo.acs.rest.boudanries.UserBoundary;


@Component
public class UserConverter {
	
	public UserBoundary fromEntity(UserEntity entity) {
		UserBoundary rv = new UserBoundary();
		rv.setAvatar(entity.getAvatar());
		rv.setRole(entity.getRole());
		rv.setUserId(entity.getUserId());
		rv.setUsername(entity.getUsername());
		return rv;
	}
	
	public UserEntity toEntity(UserBoundary boundry) {
		UserEntity rv = new UserEntity();
		rv.setAvatar(boundry.getAvatar());
		rv.setRole(boundry.getRole());
		rv.setUserId(boundry.getUserId());
		rv.setUsername(boundry.getUsername());
		return rv;
	}
	
}


