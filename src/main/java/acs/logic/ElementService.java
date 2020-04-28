package acs.logic;

import acs.rest.boudanries.ElementBoundary;

import java.util.List;

public interface ElementService {

    public ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element);

    public ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID, ElementBoundary update);
    
    public List<ElementBoundary> getAll(String userDomain, String userEmail);
    
    public ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain, String elementId) throws Exception;

    public void deleteAllElements(String adminDomain, String adminEmail);

}
