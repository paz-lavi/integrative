package main.java.demo.acs.rest;

import main.java.demo.acs.data.NewUserDetails;
import main.java.demo.acs.logic.UserService;
import main.java.demo.acs.rest.boudanries.UserBoundry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @RequestMapping(path = "/acs/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundry createNewUser(@RequestBody NewUserDetails userDetails) {
        return this.userService.createNewUser(userDetails);
    }


    @RequestMapping(path = "/acs/users/login/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundry loginAndRetrieveUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.userService.loginAndRetrieveUserDetails(userDomain, userEmail);
    }

    @RequestMapping(path = "/acs/users/{userDomain}/{userEmail}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @RequestBody UserBoundry user) {
        this.userService.updateUserDetails(userDomain, userEmail, user);
    }


}
