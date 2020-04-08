package main.java.demo.acs.logic;

import main.java.demo.Constants;
import main.java.demo.acs.data.NewUserDetails;
import main.java.demo.acs.data.UserId;
import main.java.demo.acs.rest.boudanries.UserBoundry;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserService {


    @Override
    public UserBoundry createNewUser(NewUserDetails userDetails) {
        return new UserBoundry(new UserId(Constants.DOMAIN, userDetails.getEmail()),
                userDetails.getRole(), userDetails.getUsername(), userDetails.getAvatar());
    }

    @Override
    public UserBoundry loginAndRetrieveUserDetails(String userDomain, String userEmail) {
        return null;
    }

    @Override
    public void updateUserDetails(String userDomain, String userEmail, UserBoundry user) {

    }


}
