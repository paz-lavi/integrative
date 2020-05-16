package acs.logic;

import java.util.Collection;
import java.util.List;

import acs.data.Location;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.ElementIdBoundary;

public interface EnhancedElementService extends ElementService{
	
   public void bind(String managerDomain, String managerEmail, String elementDomain, String elementID, ElementIdBoundary update);
    
   public Collection<ElementBoundary> getAllChildrenOfElement(String userDomain,String  userEmail,String elementDomain, String elementId, int size, int page);
   
   public Collection<ElementBoundary> getAllParentsOfElement(String userDomain,String  userEmail,String elementDomain, String elementId, int size, int page);
   
   public List<ElementBoundary> getAll(String userDomain,String  userEmail,int size, int page);
   
   public List<ElementBoundary> getElementByName(String name, int size, int page);
   
   public List<ElementBoundary> getElementByType(String type, int size, int page);
   
   public List<ElementBoundary> getElementByLocation(Location location, int size, int page);
}