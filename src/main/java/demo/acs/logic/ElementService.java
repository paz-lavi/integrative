package demo.acs.logic;

import java.util.List;

import demo.acs.data.ElementEntity;
import demo.acs.rest.boudanries.ElementBoundary;

public interface ElementService {

    ElementBoundary create(String managerDomain, String managerEmail, ElementBoundary element);

    ElementBoundary update(String managerDomain, String managerEmail, String elementDomain, String elementID, ElementBoundary update);
    
    List<ElementBoundary> getAll(String userDomain, String userEmail);
    
    ElementBoundary getSpecificElement(String userDomain, String userEmail, String elementDomain, String elementId);

    void deleteAllElements(String adminDomain, String adminEmail);

}
