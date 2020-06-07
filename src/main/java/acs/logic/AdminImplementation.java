package acs.logic;

import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.UserBoundary;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class AdminImplementation implements EnhancedAdminService {
	private ActionServiceImplementationDB actionImplementaion;
	private UserServiceImplementationDB userImplementaion;
	private ElementServiceImplementationDB elementImplementaion;
	
	

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
    public List<UserBoundary> exportAllUsers(String adminDomain, String adminEmail) {
    	return userImplementaion.getAllUsers(adminDomain, adminEmail);
    }
    
    @Override
    public List<UserBoundary> exportAllUsers(String adminDomain, String adminEmail,int size,int page) {
    	return userImplementaion.getAllUsers(adminDomain, adminEmail,size,page);
    }

    @Override
    public ActionBoundary exportAllActions(String adminDomain, String adminEmail) {
        return (ActionBoundary) actionImplementaion.getAllActions(adminDomain, adminEmail);
    }

	

}
