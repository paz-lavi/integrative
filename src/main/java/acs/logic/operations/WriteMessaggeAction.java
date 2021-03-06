package acs.logic.operations;


import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.MessageConverter;
import acs.data.MessageEntity;
import acs.rest.boudanries.MessageBoundary;


public class WriteMessaggeAction  implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public WriteMessaggeAction() {
		System.err.println("**** WriteMessaggeAction()");
	}
	
	@Transactional 
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		MessageBoundary messageBoundry = (MessageBoundary) action.getActionAttributes().get("message");
		MessageConverter converter = new MessageConverter();
		MessageEntity messageEntity = converter.toEntity(messageBoundry);
		messageEntity.setTreated(false);
		return messageDao.save(messageEntity).getMassageBody();
	}
	
}
