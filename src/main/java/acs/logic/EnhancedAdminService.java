package acs.logic;

import java.util.List;

import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.UserBoundary;

public interface EnhancedAdminService extends AdminService{
	
    //public UserBoundary exportAllUser(String adminDomain, String adminEmail);
    public List<UserBoundary> exportAllUsers(String adminDomain,String  adminEmail,int size, int page);


}
