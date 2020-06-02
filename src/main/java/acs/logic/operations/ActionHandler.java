package acs.logic.operations;

import acs.dal.ElementDao;
import acs.data.ActionEntity;


public interface ActionHandler {
	public Object handleAction(ActionEntity action, ElementDao elementDao);
}
