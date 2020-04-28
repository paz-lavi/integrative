package acs.logic;


import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.UserBoundary;



import org.springframework.stereotype.Service;


@Service
public class AdminImplementation implements AdminService {
	private ActionImplementation actionImplementaion;
	private UserImplementation userImplementaion;
	private ElementImplementation elementImplementaion;
	
	

    @Override
    public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail) {
    		userImplementaion.deleteAllUsers(adminDomain, adminEmail);
    	}

    @Override
    public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail) {
    	elementImplementaion.deleteAllElements(adminDomain, adminEmail);
    }

    @Override
    public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail) {
    	actionImplementaion.deleteAllActions(adminDomain, adminEmail);
    }

    @Override
    public UserBoundary exportAllUsers(String adminDomain, String adminEmail) {
    	return (UserBoundary) userImplementaion.getAllUsers(adminDomain, adminEmail);
    }

    @Override
    public ActionBoundary exportAllActions(String adminDomain, String adminEmail) {
        return (ActionBoundary) actionImplementaion.getAllActions(adminDomain, adminEmail);
    }
}
