package main.java.demo;

import org.springframework.stereotype.Service;

@Service
public class AdminImplementation implements AdminInterface {


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
