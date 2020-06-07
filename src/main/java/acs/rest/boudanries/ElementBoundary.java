package acs.rest.boudanries;


import acs.data.CreatedBy;
import acs.data.ElementId;
import acs.data.Location;
import java.util.Date;
import java.util.Map;

public class ElementBoundary {
    private ElementId elementId;
    private String type;
    private String name;
    private boolean active;
    private Date createdTimestamp = new Date();
    private CreatedBy createdBy;
    private Location location;
    private Map<String, Object> elementAttributes;


    public ElementId getElementId() {
        return elementId;
    }

    public void setElementId(ElementId elementId) {
        this.elementId = elementId;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setIsActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimeStamp) {
        this.createdTimestamp = createdTimeStamp;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, Object> getElementAttributes() {
        return elementAttributes;
    }

    public void setElementAttributes(Map<String, Object> elementAttributes) {
        this.elementAttributes = elementAttributes;
    }
}
