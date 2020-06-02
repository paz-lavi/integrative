package acs.logic.operations;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;

public class GetAllElementAtributeDataAction implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public GetAllElementAtributeDataAction() {
		System.err.println("**** GetAllElementAtributeDataAction()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao) {
		
		ElementEntity elementEntity = elementDao.findById(action.getElement())
				.orElseThrow(()->new RuntimeException("could not find element by id: " + action.getElement()));
		return elementEntity.getElementAttributes();
	}

}
