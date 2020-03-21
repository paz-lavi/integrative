package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController  {
	
	private UserInterface userInterface;	
	@Autowired
	public UserController(UserInterface userInterface) {
		super();
		this.userInterface = userInterface;
	}

	
	@RequestMapping(path = "/acs/users",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundry createNewUser(
			@RequestParam(name = "email", required = false, defaultValue = "") String email,
			@RequestParam(name = "role", required = false, defaultValue = "") String role,
			@RequestParam(name = "username", required = false, defaultValue = "") String username,
			@RequestParam(name = "avatar", required = false, defaultValue = "") String avatar) {
		return this.userInterface.createNewUser(email, role, username ,avatar);
	}


	@RequestMapping(path = "/acs/users/login/{userDomain}/{userEmail}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundry loginAndRetrieveUserDitails(
			@PathVariable("userDomain") String userDomain,
			@PathVariable("userEmail") String userEmail) {
		return this.userInterface.loginAndRetrieveUserDitails(userDomain, userEmail);
	}
	
	@RequestMapping(path = "/acs/users/{userDomain}/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundry updateUserDitails(
			@PathVariable("userDomain") String userDomain,
			@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "email", required = false, defaultValue = "") String email,
			@RequestParam(name = "role", required = false, defaultValue = "") String role,
			@RequestParam(name = "username", required = false, defaultValue = "") String username,
			@RequestParam(name = "avatar", required = false, defaultValue = "") String avatar) {
		return this.userInterface.updateUserDitails(userDomain, userEmail, email, role, username, avatar);
	}
	
	
	
	

}
