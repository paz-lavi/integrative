package acs.logic;

import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.UserBoundary;
import org.springframework.stereotype.Service;


@Service
public class AdminImplementation implements AdminService {


    @Override
    public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail) {

    }

    @Override
    public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail) {

    }

    @Override
    public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail) {

    }

    @Override
    public UserBoundary exportAllUsers(String adminDomain, String adminEmail) {
        return null;
    }

    @Override
    public ActionBoundary exportAllActions(String adminDomain, String adminEmail) {
        return null;
    }
}
