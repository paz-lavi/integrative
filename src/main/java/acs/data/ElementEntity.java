package acs.data;

import java.util.Date;
import java.util.Map;
import java.util.Set;

//import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private Date createdTimeStamp = new Date();
    private UserId createdBy;
    private Location location;
    private Map<String, Object> elementAttributes;
	private Set<ElementEntity> responses;
	private ElementEntity origin;

    
    
    public ElementEntity() {
    }
    public ElementEntity(ElementId id) {
    	this.elementId = id;
    }

    public void setElementId(ElementId elementId) {
        this.elementId = elementId;
    }
    
    @Id
    @Embedded
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
    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    @Embedded
    public UserId getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserId createdBy) {
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
    
	@ManyToOne
	public ElementEntity getOrigin() {
		return origin;
	}
	
	public void setOrigin(ElementEntity origin) {
		this.origin = origin;
	}
	
	@OneToMany(mappedBy = "origin")
	public Set<ElementEntity> getResponses() {
		return responses;
	}
	
	public void setResponses(Set<ElementEntity> responses) {
		this.responses = responses;
	}
	
	public void addResponse (ElementEntity response) {
		this.responses.add(response);
		response.setOrigin(this);
	}
}
