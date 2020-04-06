package demo.acs.logic;

import demo.acs.data.NewUserDetails;
import demo.acs.rest.boudanries.UserBoundry;

public interface UserService {

    UserBoundry createNewUser(NewUserDetails userDetails);

    UserBoundry loginAndRetrieveUserDetails(String userDomain, String userEmail);

    void updateUserDetails(String userDomain, String userEmail, UserBoundry user);


}
