package main.java.demo;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ActionImplementation implements ActionInterface {

    @Override
    public JSONObject InvokeAnAction(ActionBoundary action) {
        JSONObject temp = new JSONObject();
        temp.put("key0", "Invoke An Action");
        return temp;
    }
}
