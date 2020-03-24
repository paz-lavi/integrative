package demo;

import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserInterface {


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
