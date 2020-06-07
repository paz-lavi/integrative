package acs.logic;


import acs.rest.boudanries.ActionBoundary;

import java.util.List;

public interface ActionService {


    public Object InvokeAction(ActionBoundary action);

    public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail);

    public void deleteAllActions(String adminDomain, String adminEmail);



}
