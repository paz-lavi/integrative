package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserInterface{

	private String domain = "2020.demo";

	@Override
	public UserBoundry createNewUser(NewUserDetails userDetails) {
		return new UserBoundry(new UserId(this.domain, userDetails.getEmail()), 
				userDetails.getRole(), userDetails.getUsername() , userDetails.getAvatar()) ;
	}

	@Override
	public UserBoundry loginAndRetrieveUserDitails(String userDomain, String userEmail){
		return null;
	}

	@Override
	public UserBoundry updateUserDitails(String userDomain, String userEmail, UserBoundry user ) {
		return user;
	}
	
	
	


}
