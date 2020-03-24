package demo;

public interface UserInterface {

    public UserBoundry createNewUser(NewUserDetails userDetails);

    public UserBoundry loginAndRetrieveUserDetails(String userDomain, String userEmail);

    public UserBoundry updateUserDetails(String userDomain, String userEmail, UserBoundry user);


}
