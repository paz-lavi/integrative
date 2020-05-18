package acs.logic;

import java.util.List;

import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.UserBoundary;

public interface AdminService {

    public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail);

    public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail);


    public List<UserBoundary> exportAllUsers(String adminDomain, String adminEmail);

    public ActionBoundary exportAllActions(String adminDomain, String adminEmail);


}
