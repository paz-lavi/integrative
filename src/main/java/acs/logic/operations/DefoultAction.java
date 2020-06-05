package acs.logic.operations;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;

public class DefoultAction implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public DefoultAction() {
		System.err.println("**** DeleteOperation()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		return action;
	}

}
