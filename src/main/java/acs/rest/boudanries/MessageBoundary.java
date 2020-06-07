package acs.rest.boudanries;


import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import acs.data.UserId;

public class MessageBoundary {
	private int massageId;
	private Date createdTimestamp;
	private String massageBody;
	private UserId invokedBy;
	private boolean treated;
	
	public MessageBoundary() {
		
	}
    
	@Id
	public int getMassageId() {
		return massageId;
	}

	public void setMassageId(int massageId) {
		this.massageId = massageId;
	}
   
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getMassageBody() {
		return massageBody;
	}

	public void setMassageBody(String massageBody) {
		this.massageBody = massageBody;
	}

	public UserId getInvokedBy() {
		return invokedBy;
	}

	public void setInvokedBy(UserId invokedBy) {
		this.invokedBy = invokedBy;
	}

	public boolean getTreated() {
		return treated;
	}

	public void setTreated(boolean treated) {
		this.treated = treated;
	}
	
	

}
