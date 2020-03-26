package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserInterface userInterface;

    @Autowired
    public UserController(UserInterface userInterface) {
        super();
        this.userInterface = userInterface;
    }

    @RequestMapping(path = "/acs/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundry createNewUser(@RequestBody NewUserDetails userDetails) {
        return this.userInterface.createNewUser(userDetails);
    }


    @RequestMapping(path = "/acs/users/login/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundry loginAndRetrieveUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.userInterface.loginAndRetrieveUserDetails(userDomain, userEmail);
    }

    @RequestMapping(path = "/acs/users/{userDomain}/{userEmail}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @RequestBody UserBoundry user) {
        this.userInterface.updateUserDetails(userDomain, userEmail, user);
    }


}
