package demo;

import org.springframework.web.bind.annotation.PathVariable;

public interface AdminInterface {
	
	public void deleteAllUsersInTheSystem(String adminDomain, String adminEmail);
	
	public void deleteAllElementsInTheSystem(String adminDomain, String adminEmail);

	public void deleteAllActionsInTheSystem(String adminDomain, String adminEmail);
	
	
	UserBoundry exportAllUsers(String adminDomain,String adminEmail);
	
	ActionBoundary exportAllActions(String adminDomain, String adminEmail);
	
	
	
}
