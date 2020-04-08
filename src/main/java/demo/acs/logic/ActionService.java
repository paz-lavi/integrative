package main.java.demo.acs.logic;

import main.java.demo.acs.rest.boudanries.ActionBoundary;
import net.minidev.json.JSONObject;

public interface ActionService {


    JSONObject InvokeAnAction(ActionBoundary action);
}
