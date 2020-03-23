package demo;

public interface UserInterface {
	
	public UserBoundry createNewUser(NewUserDetails userDetails);
	public UserBoundry loginAndRetrieveUserDitails(String userDomain, String userEmail);
	public UserBoundry updateUserDitails(String userDomain, String userEmail, UserBoundry user);



}
