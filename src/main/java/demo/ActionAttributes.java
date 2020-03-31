package demo;

import java.util.Map;

public class ActionAttributes {

    private Map<String, Object> map;

    public ActionAttributes() {

    }

    public ActionAttributes(Map<String, Object> map) {
        super();
        this.map = map;

    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

}
