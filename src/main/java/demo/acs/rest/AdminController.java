package demo.acs.rest;

import demo.acs.logic.AdminService;
import demo.acs.rest.boudanries.ActionBoundary;
import demo.acs.rest.boudanries.UserBoundry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    private AdminService adminService;


    @Autowired
    public AdminController(AdminService adminService) {
        super();
        this.adminService = adminService;
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
    public UserBoundry exportAllUsers(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {
        return this.adminService.exportAllUsers(adminDomain, adminEmail);
    }


    //Export all actions        #(The last one in the list)

    @RequestMapping(path = "/acs/admin/actions/{adminDomain}/{adminEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionBoundary exportAllActions(
            @PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {
        return this.adminService.exportAllActions(adminDomain, adminEmail);
    }

}