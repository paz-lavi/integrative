package acs.logic.operations;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.dal.MessageDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;
import acs.rest.boudanries.ElementBoundary;


public class TurnOffSprinklersByLocationAction implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public TurnOffSprinklersByLocationAction() {
		System.err.println("**** TurnOnSprinklersByLocationAction()");
	}
	
	@Transactional 
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao, MessageDao messageDao) {
		
		List<ElementEntity> sprinklers = elementDao.findAllByLocation_LatBetweenAndLocation_LngBetweenAndActive(
				(int)action.getActionAttributes().get("lat") - (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lat") + (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lng") - (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lng") + (int)action.getActionAttributes().get("dist"), false);
		
		return ((List<ElementEntity>) elementDao.saveAll(
		    sprinklers 
			.stream() 
			.map(e -> { 
				Map<String, Object> elementAttributes = e.getElementAttributes();
				if(elementAttributes == null)
					elementAttributes = new HashMap<String, Object>();
				elementAttributes.put("off", true);
				e.setElementAttributes(elementAttributes);
				return e;	
			}) 
			.collect(Collectors.toList())))
				.stream()
				.map(e -> e.getLocation())
				.collect(Collectors.toList())
				.toArray(new ElementBoundary[0]);
	}
}
