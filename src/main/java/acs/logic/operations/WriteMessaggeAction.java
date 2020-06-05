package acs.logic.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;
import acs.data.MessageConverter;
import acs.data.MessageEntity;
import acs.rest.boudanries.ElementBoundary;
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
		return messageDao.save(messageEntity);
	}
}
