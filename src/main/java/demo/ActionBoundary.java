package demo;

import java.util.Date;

public class ActionBoundary {
	private ActionId actionId;
	private String type;
	private ElementId element;
	private Date createdTimeStamp = new Date();
	private UserId invokedBy;
	private ActionAttributes actionAttributes;
	
	
	public ActionBoundary() {
		
	}
	
	public ActionBoundary(ActionId actionId,String type,ElementId element,Date createdTimeStamp,UserId invokedBy,ActionAttributes actionAttributes) {
		this.actionId=actionId;
		this.type=type;
		this.element=element;
		this.createdTimeStamp=createdTimeStamp;
		this.invokedBy=invokedBy;
		this.actionAttributes=actionAttributes;
		
		
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

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public UserId getInvokedBy() {
		return invokedBy;
	}

	public void setInvokedBy(UserId invokedBy) {
		this.invokedBy = invokedBy;
	}

	public ActionAttributes getActionAttributes() {
		return actionAttributes;
	}

	public void setActionAttributes(ActionAttributes actionAttributes) {
		this.actionAttributes = actionAttributes;
	}
	
	
	
	


}
