package demo.acs.logic;

import demo.acs.data.Element;
import demo.acs.rest.boudanries.ElementBoundary;

public interface ElementService {

    ElementBoundary createNewElement(Element element);

    void updateElementDetails(String domain, String email, String managerDomain, String managerEmail, ElementBoundary element);

    ElementBoundary retrieveSpecificElements(String userDomain, String userEmail, String elementDomain, String elementId);

    ElementBoundary getAllElements(String userDomain, String userEmail);


}
