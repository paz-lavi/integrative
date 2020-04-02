package main.java.demo;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {


    private ActionInterface actionInterface;

    //Invoke an action;
    @Autowired
    public ActionController(ActionInterface actionInterface) {
        super();
        this.actionInterface = actionInterface;
    }


    @RequestMapping(path = "/acs/actions",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject InvokeAnAction(@RequestBody ActionBoundary action) {
        return this.actionInterface.InvokeAnAction(action);
    }

}
