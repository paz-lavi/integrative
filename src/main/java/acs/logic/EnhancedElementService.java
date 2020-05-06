package acs.logic;

import java.util.Set;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.ElementIdBoundary;

public interface EnhancedElementService extends ElementService{
		
	
    public void bind(String managerDomain, String managerEmail, String elementDomain, String elementID, ElementIdBoundary update);
    
   public Set<ElementBoundary> getAllChildrensOfElement(String userDomain,String  userEmail,String elementDomain, String elementId);
   
   public Set<ElementBoundary> getAllParentsOfElement(String userDomain,String  userEmail,String elementDomain, String elementId);

}