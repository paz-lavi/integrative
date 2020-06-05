package acs.logic.operations;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;
import acs.rest.boudanries.ElementBoundary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

public class TurnOffSprinklersBeloningToSensorAction  implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public TurnOffSprinklersBeloningToSensorAction() {
		System.err.println("**** TurnOffSprinklersBeloningToSensorAction()");
	}
	
	@Transactional 
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		List<ElementEntity> children = elementDao.findAllByparent_elementIdAndActive(action.getElement(), true);
		
		List<ElementEntity> rv = (List<ElementEntity>) elementDao.saveAll(
		children // List<ElementEntity>
		.stream() // Stream<ElementEntity>
		.map(e -> { 
			Map<String, Object> elementAttributes = e.getElementAttributes();
			if(elementAttributes == null)
				elementAttributes = new HashMap<String, Object>();
			elementAttributes.put("off", true);
			e.setElementAttributes(elementAttributes);
			return e;	
		}) // Stream<ElementBoundary>
		.collect(Collectors.toList()));
		
		return rv.stream().
				map(e -> e.getLocation())
				.collect(Collectors.toList())
				.toArray(new ElementBoundary[0]);
	}

}
