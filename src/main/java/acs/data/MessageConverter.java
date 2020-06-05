package acs.data;

import org.springframework.stereotype.Component;

import acs.rest.boudanries.ActionBoundary;
import acs.rest.boudanries.MessageBoundary;

@Component
public class MessageConverter {
	
	 public MessageBoundary fromEntity(MessageEntity entity) {
		 MessageBoundary rv = new MessageBoundary();
		 rv.setCreatedTimestamp(entity.getCreatedTimestamp());
		 rv.setInvokedBy(entity.getInvokedBy());
		 rv.setMassageBody(entity.getMassageBody());
		 rv.setMassageId(entity.getMassageId());	
	        return rv;
	    }

	    public MessageEntity toEntity(MessageBoundary boundary) {
	    	MessageEntity rv = new MessageEntity();
	    	rv.setInvokedBy(boundary.getInvokedBy());
	    	rv.setMassageBody(boundary.getMassageBody());
	    	rv.setMassageId(MessageIdGenerator.nextValue());
	        return rv;
	    }
	

}
