package acs.data;

import javax.persistence.Embeddable;

@Embeddable
public class CreatedBy {
	UserId userId;

	public CreatedBy() {
		super();
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}
	
	

}
