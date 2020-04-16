package demo.acs.rest.boudanries;

import demo.acs.data.ElementId;
import demo.acs.data.Location;
import demo.acs.data.UserId;

import java.util.Date;
import java.util.Map;

public class ElementBoundary {
    private ElementId elementId;
    private String type;
    private String name;
    private boolean active;
    private Date createdTimeStamp = new Date();
    private UserId createdBy;
    private Location location;
    private Map<String, Object> elementAttributes;

    public ElementBoundary() {

    }

    public ElementBoundary(ElementId elementId, String type, String name, boolean active, Date createdTimeStamp,
                           UserId createdBy, Location location, Map<String, Object> elementAttributes) {
        super();
        this.elementId = elementId;
        this.type = type;
        this.name = name;
        this.active = active;
        this.createdTimeStamp = createdTimeStamp;
        this.createdBy = createdBy;
        this.location = location;
        this.elementAttributes = elementAttributes;
    }

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

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public UserId getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserId createdBy) {
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
