package acs.logic.operations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.MessageConverter;
import acs.data.MessageEntity;
import acs.data.UserId;
import acs.rest.boudanries.MessageBoundary;


public class GetUntreatedMessagesByUserAction  implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public GetUntreatedMessagesByUserAction() {
		System.err.println("**** GetTreatedMessagesByUserAction()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		List<MessageEntity> messagges = messageDao.findAllByInvokedByAndTreated((UserId) action.getActionAttributes().get("user masseges"),
				false,
				PageRequest.of((int)action.getActionAttributes().get("page"),
				(int)action.getActionAttributes().get("size"), Direction.DESC, "createdTimestamp"));
		
		MessageConverter converter = new MessageConverter();
		return messagges.stream()
				.map(converter::fromEntity)
				.collect(Collectors.toSet())
				.toArray(new MessageBoundary[0]);
	}
}
