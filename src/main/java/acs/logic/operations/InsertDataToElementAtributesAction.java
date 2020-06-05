package acs.logic.operations;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;

public class InsertDataToElementAtributesAction  implements ActionHandler {

	// this class must have a default constructor to support implementation based on java reflection
	public InsertDataToElementAtributesAction() {
		System.err.println("**** InsertDataToElementAtributesAction()");
	}
	

	
	@Transactional
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		Map<String, Object> actionAtributes = action.getActionAttributes();
		Map<String, Object> data = (Map<String, Object>) actionAtributes.get("insert data");
		
		ElementEntity elementEntity = elementDao.findById(action.getElement())
				.orElseThrow(()->new RuntimeException("could not find element by id: " + action.getElement()));
		Map<String, Object> elementAttributes = elementEntity.getElementAttributes();
		
		for (Entry<String, Object> entry : data.entrySet())  
			elementAttributes.put(entry.getKey(), entry.getValue());
		
		elementEntity.setElementAttributes(elementAttributes);
		
		return elementDao.save(elementEntity).getElementAttributes();
	}
}
