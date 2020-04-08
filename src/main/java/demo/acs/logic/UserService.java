package main.java.demo.acs.logic;

import main.java.demo.acs.data.NewUserDetails;
import main.java.demo.acs.rest.boudanries.UserBoundry;

public interface UserService {

    UserBoundry createNewUser(NewUserDetails userDetails);

    UserBoundry loginAndRetrieveUserDetails(String userDomain, String userEmail);

    void updateUserDetails(String userDomain, String userEmail, UserBoundry user);


}
