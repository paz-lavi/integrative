package acs.logic.operations;

import acs.dal.ElementDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;


public class TurnOnSprinklersBeloningToSensorAction implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public TurnOnSprinklersBeloningToSensorAction() {
		System.err.println("**** TurnOnSprinklersBeloningToSensorAction()");
	}
	
	@Transactional 
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao) {
		List<ElementEntity> children = elementDao.findAllByparent_elementIdAndActive(action.getElement(), true);
		return elementDao.saveAll(
		children // List<ElementEntity>
		.stream() // Stream<ElementEntity>
		.map(e -> { 
			Map<String, Object> elementAttributes = e.getElementAttributes();
			if(elementAttributes == null)
				elementAttributes = new HashMap<String, Object>();
			elementAttributes.put("on", true);
			e.setElementAttributes(elementAttributes);
			return e;	
		}) // Stream<ElementBoundary>
		.collect(Collectors.toList()));
	}

}
