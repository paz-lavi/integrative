package acs.rest.boudanries;

import acs.data.ActionId;
import acs.data.ElementId;
import acs.data.UserId;

import java.util.Date;
import java.util.Map;

public class ActionBoundary {
    private ActionId actionId;
    private String type;
    private ElementId element;
    private Date createdTimeStamp = new Date();
    private UserId invokedBy;
    private Map<String, Object> actionAttributes;


    public ActionBoundary() {

    }

    public ActionId getActionId() {
        return actionId;
    }

    public void setActionId(ActionId actionId) {
        this.actionId = actionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ElementId getElement() {
        return element;
    }

    public void setElement(ElementId element) {
        this.element = element;
    }

    public Date getCreatedTimestamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimestamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public UserId getInvokedBy() {
        return invokedBy;
    }

    public void setInvokedBy(UserId invokedBy) {
        this.invokedBy = invokedBy;
    }

    public Map<String, Object> getActionAttributes() {
        return actionAttributes;
    }

    public void setActionAttributes(Map<String, Object> actionAttributes) {
        this.actionAttributes = actionAttributes;
    }


}
