package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserImplementation implements UserInterface{
	
    private List<User> users = new ArrayList<User>(); 
	private String domain = "2020.demo";

	@Override
	public UserBoundry createNewUser(String email, String role, String username, String avatar) {
		UserId userId = new UserId(domain, email);
		User user = new User(userId, role, username, avatar);
		users.add(user);
		return new UserBoundry(userId, role, username, avatar);
	}

	@Override
	public UserBoundry loginAndRetrieveUserDitails(String userDomain, String userEmail){
		UserId userId = new UserId(userDomain, userEmail);
		User user = this.users.stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
		return new UserBoundry(user.getUserId(), user.getRole(), user.getUsername(), user.getAvatar());
	}

	@Override
	public UserBoundry updateUserDitails(String userDomain, String userEmail, String email, String role,
			String username, String avatar) {
		UserId userId = new UserId(userDomain, userEmail);
		return null;
	}
	
	
	


}
