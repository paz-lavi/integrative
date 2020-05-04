package acs.logic;

import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.data.ActionId;
import acs.rest.boudanries.ActionBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
public class ActionImplementation implements ActionService {
    private ActionConverter actionConverter;
    private Map<ActionId, ActionEntity> actionsDatabase;
    
    
    @Autowired
    public ActionImplementation(ActionConverter actionConverter) {
        this.actionConverter = actionConverter;
    }
    
    @PostConstruct
    public void init() {
        // since this class is a singleton, we generate a thread safe collection
        this.actionsDatabase = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public Object InvokeAction(ActionBoundary action) {
        actionsDatabase.put(action.getActionId(), this.actionConverter.toEntity(action));
        return (Object)action;
    }

    @Override
    public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail) {
        return this.actionsDatabase
                .values()
                .stream()
                .map(this.actionConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllActions(String adminDomain, String adminEmail) {
        actionsDatabase.clear();
    }
}
