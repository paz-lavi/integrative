package demo;

public interface UserInterface {
	
	public UserBoundry createNewUser(String email, String role, String username, String avatar);
	public UserBoundry loginAndRetrieveUserDitails(String userDomain, String userEmail);
	public UserBoundry updateUserDitails(String userDomain, String userEmail, String email, String role, String username, String avatar);



}
