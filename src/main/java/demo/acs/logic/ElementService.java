package demo.acs.logic;

import demo.acs.data.Element;
import demo.acs.rest.boudanries.ElementBoundry;

public interface ElementService {

    ElementBoundry createNewElement(Element element);

    void updateElementDetails(String domain, String email, String managerDomain, String managerEmail, ElementBoundry element);

    ElementBoundry retrieveSpecificElements(String userDomain, String userEmail, String elementDomain, String elementId);

    ElementBoundry getAllElements(String userDomain, String userEmail);


}