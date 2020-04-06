package demo.acs.logic;

import demo.acs.rest.boudanries.ActionBoundary;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ActionImplementation implements ActionService {

    @Override
    public JSONObject InvokeAnAction(ActionBoundary action) {
        JSONObject temp = new JSONObject();
        temp.put("key0", "Invoke An Action");
        return temp;
    }
}
