package demo;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ActionImplementation implements ActionInterface {

    @Override
    public JSONObject InvokeAnAction(ActionBoundary action) {
        return null;
    }
}
