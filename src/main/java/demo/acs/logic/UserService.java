package main.java.demo.acs.logic;

import java.util.List;

import main.java.demo.acs.data.NewUserDetails;
import main.java.demo.acs.rest.boudanries.UserBoundry;

public interface UserService {

    UserBoundry createUser(UserBoundry boundry);
    
    UserBoundry login(String userDomain, String userEmail);
    
    UserBoundry updateUser(String userDomain, String userEmail, UserBoundry update);
    
    List<UserBoundry> getAllUsers(String adminDomain, String adminEmail);
    
    void deleteAllUsers(String adminDomain, String adminEma);

}
