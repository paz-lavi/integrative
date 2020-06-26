package acs.logic;


import acs.aop.MyLogger;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.data.ActionId;
import acs.data.ActionIdGenerator;
import acs.rest.boudanries.ActionBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActionImplementation implements ActionService {
    private ActionConverter actionConverter;
    private Map<ActionId, ActionEntity> actionsDatabase;
    private String domain;

    
    
 // injection of value from the spring boot configuration
  	@Value("${spring.application.name:demo}")
  	public void setProjectName(String domain) {
  		this.domain = domain;
  	}

    
    @Autowired
    public ActionImplementation(ActionConverter actionConverter) {
        this.actionConverter = actionConverter;
    }
    
    @PostConstruct
    public void init() {
        // since this class is a singleton, we generate a thread safe collection
        this.actionsDatabase = Collections.synchronizedMap(new HashMap<>());
    }
    @MyLogger
    @Override
    public Object InvokeAction(ActionBoundary action) {
    	ActionId aid = new ActionId();
    	aid.setDomain(this.domain);
    	aid.setId(ActionIdGenerator.nextValue());
    	action.setActionId(aid);
        actionsDatabase.put(action.getActionId(), this.actionConverter.toEntity(action));
        return (Object)action;
    }
    @MyLogger
    @Override
    public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail) {
        return this.actionsDatabase
                .values()
                .stream()
                .map(this.actionConverter::fromEntity)
                .collect(Collectors.toList());
    }
    @MyLogger
    @Override
    public void deleteAllActions(String adminDomain, String adminEmail) {
        actionsDatabase.clear();
    }
}
