package main.java.demo.acs.logic;

import main.java.demo.acs.rest.boudanries.ActionBoundary;
import main.java.demo.acs.rest.boudanries.UserBoundry;
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
    public UserBoundry exportAllUsers(String adminDomain, String adminEmail) {
        return null;
    }

    @Override
    public ActionBoundary exportAllActions(String adminDomain, String adminEmail) {
        return null;
    }
}
