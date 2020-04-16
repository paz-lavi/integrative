package demo.acs.logic;

import demo.acs.rest.boudanries.UserBoundary;

import java.util.List;

public interface UserService {

    UserBoundary createUser(UserBoundary boundry);

    UserBoundary login(String userDomain, String userEmail);

    UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update);

    List<UserBoundary> getAllUsers(String adminDomain, String adminEmail);

    void deleteAllUsers(String adminDomain, String adminEmail);


}
