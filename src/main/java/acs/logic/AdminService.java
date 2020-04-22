package acs.logic;

import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.UserBoundary;

public interface AdminService {

    public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail);


    UserBoundary exportAllUsers(String adminDomain, String adminEmail);

    ActionBoundary exportAllActions(String adminDomain, String adminEmail);


}
