package main.java.demo.acs.logic;

import main.java.demo.acs.rest.boudanries.ActionBoundary;
import main.java.demo.acs.rest.boudanries.UserBoundry;

public interface AdminService {

    public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail);


    UserBoundry exportAllUsers(String adminDomain, String adminEmail);

    ActionBoundary exportAllActions(String adminDomain, String adminEmail);


}
