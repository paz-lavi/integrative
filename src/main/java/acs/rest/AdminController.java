package acs.rest;

import acs.logic.AdminService;
import acs.logic.EnhancedActionService;
import acs.logic.EnhancedAdminService;
import acs.logic.EnhancedUserService;
import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.UserBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    private EnhancedAdminService adminService;
    private EnhancedUserService userService;
    private EnhancedActionService actionService;
    
   

    @Autowired
    public AdminController(EnhancedAdminService adminService,EnhancedUserService userService,EnhancedActionService actionService) {
        super();
        this.adminService = adminService;
        this.userService=userService;
        this.actionService=actionService;
    }


//delete all users in the system       #the first one

    @RequestMapping(path = "/acs/admin/users/{adminDomain}/{adminEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllUsersInTheSystem(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {

        //The users deleted here
        this.adminService.deleteAllUsersInTheSystem(adminDomain, adminEmail);

    }


    //delete all Elements in the system       #the second

    @RequestMapping(path = "/acs/admin/elements/{adminDomain}/{adminEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllElementsInTheSystem(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {

        //The Elements deleted here
        this.adminService.deleteAllElementsInTheSystem(adminDomain, adminEmail);

    }


    //delete all Actions in the system       #the third

    @RequestMapping(path = "/acs/admin/actions/{adminDomain}/{adminEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllActionsInTheSystem(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {

        //The Elements deleted here
        this.adminService.deleteAllActionsInTheSystem(adminDomain, adminEmail);

    }


    //Export all users       #the 4

    @RequestMapping(path = "/acs/admin/users/{adminDomain}/{adminEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary[] exportAllUsers(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail, 
    		@RequestParam (name = "size", required = false, defaultValue = "3") int size,
    		@RequestParam (name = "page", required = false, defaultValue = "0") int page)
    {
      //  return this.adminService.exportAllUsers(adminDomain, adminEmail, size, page).toArray(new UserBoundary[0]);
    	return this.userService.getAllUsers(adminDomain, adminEmail,size,page).toArray(new UserBoundary[0]);
    	

    }
    


    //Export all actions        #(The last one in the list)

    @RequestMapping(path = "/acs/admin/actions/{adminDomain}/{adminEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionBoundary[] exportAllActions(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail,
        	@RequestParam (name = "size", required = false, defaultValue = "3") int size,
    		@RequestParam (name = "page", required = false, defaultValue = "0") int page) {
        return this.actionService.getAllActions(adminDomain, adminEmail,size,page).toArray(new ActionBoundary[0]);
    }

}
