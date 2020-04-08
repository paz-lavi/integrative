package main.java.demo.acs.rest;

import main.java.demo.acs.logic.ActionService;
import main.java.demo.acs.rest.boudanries.ActionBoundary;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public JSONObject InvokeAnAction(@RequestBody ActionBoundary action) {
        return this.actionService.InvokeAnAction(action);
    }

}
