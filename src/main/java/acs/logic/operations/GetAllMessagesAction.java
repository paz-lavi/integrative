package acs.logic.operations;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.MessageConverter;
import acs.data.MessageEntity;

import acs.rest.boudanries.MessageBoundary;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;



public class GetAllMessagesAction implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public GetAllMessagesAction() {
		System.err.println("**** GetAllMessagesAction()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		Page<MessageEntity> messagges = messageDao.findAll(PageRequest.of((int)action.getActionAttributes().get("page"),
				(int)action.getActionAttributes().get("size"), Direction.DESC, "createdTimestamp"));
		
		MessageConverter converter = new MessageConverter();
		return messagges.stream()
				.map(converter::fromEntity)
				.collect(Collectors.toSet())
				.toArray(new MessageBoundary[0]);
	}

	
	

}
