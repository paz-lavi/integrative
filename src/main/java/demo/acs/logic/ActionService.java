package demo.acs.logic;

import demo.acs.rest.boudanries.ActionBoundary;
import net.minidev.json.JSONObject;

public interface ActionService {


    JSONObject InvokeAnAction(ActionBoundary action);
}
