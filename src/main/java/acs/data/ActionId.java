package acs.data;

import java.io.Serializable;

import javax.persistence.Column;
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
    @Column(name = "action_domain")
    public String getDomain() {
        return actionDomain;
    }

    public void setDomain(String actionDomain) {
        this.actionDomain = actionDomain;
    }
    @Column(name = "action_id")
    public int getId() {
        return actionID;
    }

    public void setId(int actionID) {
        this.actionID = actionID;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionDomain == null) ? 0 : actionDomain.hashCode());
		result = prime * result + actionID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionId other = (ActionId) obj;
		if (actionDomain == null) {
			if (other.actionDomain != null)
				return false;
		} else if (!actionDomain.equals(other.actionDomain))
			return false;
		if (actionID != other.actionID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActionId [domain=" + actionDomain + ", id=" + actionID + "]";
	}
}
