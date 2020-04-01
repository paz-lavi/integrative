package main.java.demo;

public interface UserInterface {

     UserBoundry createNewUser(NewUserDetails userDetails);

     UserBoundry loginAndRetrieveUserDetails(String userDomain, String userEmail);

     void updateUserDetails(String userDomain, String userEmail, UserBoundry user);


}
