package main.java.demo.acs.logic;

import java.util.List;
import java.util.Map;

import main.java.demo.acs.rest.boudanries.UserBoundary;

public interface UserService {

    UserBoundary createUser(UserBoundary boundry);
    
    UserBoundary login(String userDomain, String userEmail);
    
    UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update);
    
    List<UserBoundary> getAllUsers(String adminDomain, String adminEmail);
    
    void deleteAllUsers(String adminDomain, String adminEma);

	Map<String, Object> getProjectName();

}
