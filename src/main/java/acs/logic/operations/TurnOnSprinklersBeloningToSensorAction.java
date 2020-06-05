package acs.logic.operations;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;
import acs.rest.boudanries.ElementBoundary;

import java.util.Collection;
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
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		List<ElementEntity> children = elementDao.findAllByparent_elementIdAndActive(action.getElement(), true);
		return ((List<ElementEntity>) elementDao.saveAll(
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
		.collect(Collectors.toList())))
				.stream()
				.map(e -> e.getLocation())
				.collect(Collectors.toList())
				.toArray(new ElementBoundary[0]);
	}

}
