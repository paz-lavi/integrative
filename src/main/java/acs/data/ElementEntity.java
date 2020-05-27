package acs.data;

import java.util.Date;
import java.util.Map;
import java.util.Set;

//import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import acs.dal.MapToJsonConverter;


@Entity
@Table(name="Elements")
public class ElementEntity {
	private ElementId elementId;
    private String type;
    private String name;
    private boolean isActive;
    private Date createdTimestamp = new Date();
    private CreatedBy createdBy;
    private Location location;
    private Map<String, Object> elementAttributes;
	private Set<ElementEntity> children;
	private ElementEntity parent;

    
    
    public ElementEntity() {
    }
    
    public void setElementId(ElementId elementId) {
        this.elementId = elementId;
    }
    @EmbeddedId
    public ElementId getElementId() {
    	return this.elementId;
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

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Embedded
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

	@Convert(converter = MapToJsonConverter.class)
	@Lob
    public Map<String, Object> getElementAttributes() {
        return elementAttributes;
    }

    public void setElementAttributes(Map<String, Object> elementAttributes) {
        this.elementAttributes = elementAttributes;
    }
    
	@ManyToOne(fetch = FetchType.LAZY)
	public ElementEntity getParent() {
		return parent;
	}
	
	public void setParent(ElementEntity parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<ElementEntity> getChildren() {
		return children;
	}
	
	public void setChildren(Set<ElementEntity> children) {
		this.children = children;
	}
	
	public void addChild (ElementEntity child) {
		this.children.add(child);
	}
	
	
}
