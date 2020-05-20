package acs.data;

import acs.rest.boudanries.UserBoundary;
import acs.data.NewUserDetails;
import acs.data.UserEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
private String domain;
	
	// injection of value from the spring boot configuration
	@Value("${spring.application.name:demo}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

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
		
	public UserBoundary fromUserDitails(NewUserDetails newUserDetails) {
		UserBoundary rv = new UserBoundary();
		
		UserId id = new UserId();
		id.setDomain(domain);
		id.setEmail(newUserDetails.getEmail());
		rv.setUserId(id);
		
		rv.setAvatar(newUserDetails.getAvatar());	
		rv.setRole(newUserDetails.getRole());	
		rv.setUsername(newUserDetails.getUsername());		
		return rv;
	}
	
}


