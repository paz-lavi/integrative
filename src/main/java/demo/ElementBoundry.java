package demo;

import java.util.Date;

public class ElementBoundry {
    private ElementId elementId;
    private String type;
    private String name;
    private boolean active;
    private Date createdTimeStamp = new Date();
    private UserId createdBy;
    private Location location;
    private ElementAttributes elementAttributes;

    public ElementBoundry() {

    }

    public ElementBoundry(ElementId elementId, String type, String name, boolean active, Date createdTimeStamp,
                          UserId createdBy, Location location, ElementAttributes elementAttributes) {
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

    public ElementAttributes getElementAttributes() {
        return elementAttributes;
    }

    public void setElementAttributes(ElementAttributes elementAttributes) {
        this.elementAttributes = elementAttributes;
    }
}
