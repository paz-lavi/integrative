package acs.logic.operations;

import acs.dal.ElementDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;

public class DeleteAction implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public DeleteAction() {
		System.err.println("**** DeleteOperation()");
	}
	
	@Override
	public void handleAction(ActionEntity action, ElementDao elementDao) {
		ElementEntity elementEntity = elementDao.findById(action.getElement())
				.orElseThrow(()->new RuntimeException("could not find element by id: " + action.getElement()));
		elementEntity.setActive(false);
		elementDao.save(elementEntity);
	}

}
