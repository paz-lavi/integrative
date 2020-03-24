package demo;

import org.springframework.stereotype.Service;

@Service
public class ElementImplementation implements ElementInterface {

    @Override
    public ElementBoundry createNewElement(Element element) {
        return new ElementBoundry(new ElementId(Constants.DOMAIN, 54), element.getType(), element.getName()
                , element.isActive(), element.getCreatedTimeStamp(), element.getCreatedBy()
                , element.getLocation(), element.getElementAttributes());
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
