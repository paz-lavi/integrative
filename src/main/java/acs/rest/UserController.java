package acs.rest;

import acs.rest.boudanries.UserBoundary;
import acs.data.NewUserDetails;
import acs.data.UserConverter;
import acs.logic.IncorrectInputExeption;
import acs.logic.InsafitiontInputExeption;
import acs.logic.UserServiceImplementationDB;
import acs.logic.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private UserServiceImplementationDB userService;
    private UserConverter userConverter;


    @Autowired
    public UserController(UserServiceImplementationDB userService, UserConverter userConverter) {
        super();
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @RequestMapping(path = "/acs/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary createNewUser(@RequestBody NewUserDetails newUserDetails) {
    	UserBoundary boundary = this.userConverter.fromUserDitails(newUserDetails);   
        return this.userService.createUser(boundary);
    }


    @RequestMapping(path = "/acs/users/login/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary loginAndRetrieveUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.userService.login(userDomain, userEmail);
    }

    @RequestMapping(path = "/acs/users/{userDomain}/{userEmail}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserDetails(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @RequestBody UserBoundary boundry) {
        this.userService.updateUser(userDomain, userEmail, boundry);
    }
    
    @RequestMapping(path = "/acs/users/getall/{adminDomain}/{adminEmail}",
    		method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary[] getAllUsers(
			@PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {
		return this.userService
				.getAllUsers(adminDomain, adminEmail)
                .toArray(new UserBoundary[0]);
	}
    
	@RequestMapping(path = "/acs/users/deleteAll/{adminDomain}/{adminEmail}", 
		    method = RequestMethod.DELETE)
	public void deleteAll(
			@PathVariable("adminDomain") String adminDomain,
            @PathVariable("adminEmail") String adminEmail) {
		this.userService
			.deleteAllUsers(adminDomain, adminEmail);
	}
    
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleException (UserNotFoundException e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"User was not found":
						e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleException (InsafitiontInputExeption e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Insafitiont input":
						e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, Object> handleException (IncorrectInputExeption e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Incorrect input":
						e.getMessage());
	}
	



}
