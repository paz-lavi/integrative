package acs.rest;

import acs.logic.ActionService;
import acs.rest.boudanries.ActionBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {


    private ActionService actionService;

    //Invoke an action;
    @Autowired
    public ActionController(ActionService actionService) {
        super();
        this.actionService = actionService;
    }


    @RequestMapping(path = "/acs/actions",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object InvokeAnAction(@RequestBody ActionBoundary action) {
        return this.actionService.InvokeAction(action);
    }
    
    
    
    
    @RequestMapping(path = "/acs/actions/{adminDomain}/{adminEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionBoundary[] getAllActions(
    		@PathVariable("adminDomain") String adminDomain
    		,@PathVariable("adminEmail")String adminEmail) {
        return this.actionService.getAllActions(adminDomain,adminEmail).toArray(new ActionBoundary[0]);
    }
    
    
    

}
