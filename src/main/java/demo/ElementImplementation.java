package demo;

import org.springframework.stereotype.Service;

@Service
public class ElementImplementation implements ElementInterface {

    @Override
    public ElementBoundry createNewElement(Element element) {
        return null;
    }

    @Override
    public void updateElementDetails(String domain, String email, String managerDomain, String managerEmail, ElementBoundry element) {

    }

    @Override
    public ElementBoundry retrieveSpecificElements(String userDomain, String userEmail, String elementDomain, String elementId) {
        return null;
    }

    @Override
    public ElementBoundry getAllElements(String userDomain, String userEmail) {
        return null;
    }
}
