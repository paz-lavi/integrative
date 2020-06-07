package acs.logic.operations;

import java.util.HashMap;
import java.util.Map;


import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;


public class GetSpecificDataFromElementAtributeAction implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public GetSpecificDataFromElementAtributeAction() {
		System.err.println("**** GetSpecificDataFromElementAtributeAction()");
	}
	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		Map<String, Object> actionAtributes = action.getActionAttributes();
		String[] data = (String[]) actionAtributes.get("get data");
		
		ElementEntity elementEntity = elementDao.findById(action.getElement())
				.orElseThrow(()->new RuntimeException("could not find element by id: " + action.getElement()));
		Map<String, Object> elementAttributes = elementEntity.getElementAttributes();
		
		Map<String, Object> rv = new HashMap<String, Object>();
		for (String d : data) { 
			Object atribute = elementAttributes.get(d);
		    if (atribute != null) {
		    	rv.put(d, atribute);	
		    }
		}
		elementEntity.setElementAttributes(elementAttributes);
		
		return elementDao.save(elementEntity).getElementAttributes();
		

	}

}
