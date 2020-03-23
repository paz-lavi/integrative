package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserInterface{

	private String domain = "2020.demo";

	@Override
	public UserBoundry createNewUser(String email, String role, String username, String avatar) {
		UserId userId = new UserId(domain, email);
		return new UserBoundry(userId, role, username, avatar);
	}

	@Override
	public UserBoundry loginAndRetrieveUserDitails(String userDomain, String userEmail){
		return null;
	}

	@Override
	public UserBoundry updateUserDitails(String userDomain, String userEmail, String email, String role,
			String username, String avatar) {
		return null;
	}
	
	
	


}
