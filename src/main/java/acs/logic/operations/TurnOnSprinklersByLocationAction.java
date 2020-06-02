package acs.logic.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import acs.dal.ElementDao;
import acs.data.ActionEntity;
import acs.data.ElementEntity;

public class TurnOnSprinklersByLocationAction  implements ActionHandler{

	// this class must have a default constructor to support implementation based on java reflection
	public TurnOnSprinklersByLocationAction() {
		System.err.println("**** TurnOnSprinklersByLocationAction()");
	}
	
	@Transactional 
	@Override
	public Object handleAction(ActionEntity action, ElementDao elementDao) {
		
		List<ElementEntity> sprinklers = elementDao.findAllByLocation_LatBetweenAndLocation_LngBetweenAndActive(
				(int)action.getActionAttributes().get("lat") - (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lat") + (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lng") - (int)action.getActionAttributes().get("dist"),
				(int)action.getActionAttributes().get("lng") + (int)action.getActionAttributes().get("dist"), false);
		
		return elementDao.saveAll(
		    sprinklers 
			.stream() 
			.map(e -> { 
				Map<String, Object> elementAttributes = e.getElementAttributes();
				if(elementAttributes == null)
					elementAttributes = new HashMap<String, Object>();
				elementAttributes.put("on", true);
				e.setElementAttributes(elementAttributes);
				return e;	
			}) 
			.collect(Collectors.toList())); 
	}
}
