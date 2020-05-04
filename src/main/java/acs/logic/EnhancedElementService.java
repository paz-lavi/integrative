package acs.logic;

import java.util.Set;

import acs.rest.boudanries.ElementBoundary;


public interface EnhancedElementService extends ElementService{
	public void connectElements (String originId, String responseId);

	public Set<ElementBoundary> getAllResponses(String originId);	
}