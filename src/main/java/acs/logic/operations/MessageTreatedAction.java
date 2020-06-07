package acs.logic.operations;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.MessageConverter;
import acs.data.MessageEntity;



public class MessageTreatedAction  implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public MessageTreatedAction() {
		System.err.println("**** MessageTreatedAction()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		MessageEntity messagge = messageDao.findById((Integer) action.getActionAttributes().get("message treated"))
				.orElseThrow(()->new RuntimeException("could not find this massage"));
		
		MessageConverter converter = new MessageConverter();
		messagge.setTreated(true);
		return converter.fromEntity(messageDao.save(messagge));
	}
}
