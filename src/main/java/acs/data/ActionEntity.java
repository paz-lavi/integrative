package acs.data;

import java.util.Date;
import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import acs.dal.MapToJsonConverter;


@Entity
@Table(name="Actions")
public class ActionEntity {
	
    private ActionId actionId;
    private String type;
    private ElementId element;
    private Date createdTimestamp;
    private UserId invokedBy;
    private Map<String, Object> actionAttributes;

    public ActionEntity() {
    }

    public ActionEntity(ActionId actionId, String type, ElementId element, Date createdTimestamp, UserId invokedBy, Map<String, Object> actionAttributes) {
        this.actionId = actionId;
        this.type = type;
        this.element = element;
        this.createdTimestamp = createdTimestamp;
        this.invokedBy = invokedBy;
        this.actionAttributes = actionAttributes;
    }

    @EmbeddedId
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
    
    
    @Embedded
    public ElementId getElement() {
        return element;
    }

    public void setElement(ElementId element) {
        this.element = element;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    
    @Embedded
    public UserId getInvokedBy() {
        return invokedBy;
    }

    public void setInvokedBy(UserId invokedBy) {
        this.invokedBy = invokedBy;
    }

    @Convert(converter = MapToJsonConverter.class)
	@Lob
    public Map<String, Object> getActionAttributes() {
        return actionAttributes;
    }

    public void setActionAttributes(Map<String, Object> actionAttributes) {
        this.actionAttributes = actionAttributes;
    }
}
