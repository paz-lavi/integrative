package acs.data;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class ActionId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionDomain;
    private int actionID;

    public ActionId() {

    }

    public ActionId(String actionDomain, int actionID) {
        super();
        this.actionDomain = actionDomain;
        this.actionID = actionID;
    }

    public String getActionDomain() {
        return actionDomain;
    }

    public void setActionDomain(String actionDomain) {
        this.actionDomain = actionDomain;
    }

    public int getActionId() {
        return actionID;
    }

    public void setActionId(int actionID) {
        this.actionID = actionID;
    }

    @Override
    public boolean equals(Object obj) {
        ActionId actionId = (ActionId) obj;
        return this.actionID == actionId.actionID;
    }
    
    
    
//    @Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//    
	
//	@Override
//	public int compareTo(Object o) {
//		ElementId id = (ElementId)o;
//		return this.getId().compareTo(id.getId());
//	}

	@Override
	public String toString() {
		return "ActionId [domain=" + actionDomain + ", id=" + actionID + "]";
	}
}
