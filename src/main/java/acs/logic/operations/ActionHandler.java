package acs.logic.operations;

import acs.dal.ElementDao;
import acs.data.ActionEntity;


public interface ActionHandler {
	public void handleAction(ActionEntity action, ElementDao elementDao);
}
