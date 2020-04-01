package main.java.demo;

public interface ElementInterface {

    ElementBoundry createNewElement(Element element);

    void updateElementDetails(String domain, String email, String managerDomain, String managerEmail, ElementBoundry element);

    ElementBoundry retrieveSpecificElements(String userDomain, String userEmail, String elementDomain, String elementId);

    ElementBoundry getAllElements(String userDomain, String userEmail);


}
