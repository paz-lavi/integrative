package acs.data;

import acs.rest.boudanries.UserBoundary;
import org.springframework.stereotype.Component;


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

	public UserEntity toEntity(UserBoundary boundary) {
		UserEntity rv = new UserEntity();
		rv.setAvatar(boundary.getAvatar());
		rv.setRole(boundary.getRole());
		rv.setUserId(boundary.getUserId());
		rv.setUsername(boundary.getUsername());
		return rv;
	}
	
}


