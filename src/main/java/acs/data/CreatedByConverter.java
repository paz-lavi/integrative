package acs.data;


import org.springframework.stereotype.Component;

@Component
public class CreatedByConverter {
	
	public CreatedBy fromUserIdToCreatedBy(UserId userId) {
		CreatedBy createdBy = new CreatedBy();
		createdBy.setUserId(userId);
		return createdBy;
	}

}
