package acs.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Massages")
public class MessageEntity {
	private int massageId;
	private Date createdTimestamp = new Date();;
	private String massageBody;
	private UserId invokedBy;
	
	public MessageEntity() {
		
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

	@Embedded
	public void setInvokedBy(UserId invokedBy) {
		this.invokedBy = invokedBy;
	}
		
}
