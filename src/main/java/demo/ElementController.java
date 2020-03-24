package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElementController {

    private ElementInterface elementInterface;

    @Autowired
    public ElementController(ElementInterface elementInterface) {
        super();
        this.elementInterface = elementInterface;
    }


    @RequestMapping(path = "/acs/elements/{managerDomain}/{managerEmail}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundry createNewElement(@RequestBody Element element) {
        return this.elementInterface.createNewElement(element);
    }

    @RequestMapping(path = "/acs/elements/{managerDomain}/{managerEmail}/{elementDomain}/{elementId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateElementDetails(
            @PathVariable("managerDomain") String managerDomain,
            @PathVariable("managerEmail") String managerEmail,
            @PathVariable("elementDomain") String elementDomain,
            @PathVariable("elementId") String elementId,
            @RequestBody ElementBoundry element) {
        this.elementInterface.updateElementDetails(managerDomain, managerEmail, elementDomain, elementId, element);
    }


    @RequestMapping(path = "/acs/{userDomain}/{userEmail}/{elementDomain}/{elementId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundry retrieveSpecificElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @PathVariable("elementDomain") String elementDomain,
            @PathVariable("elementId") String elementId) {
        return this.elementInterface.retrieveSpecificElements(userDomain, userEmail, elementDomain, elementId);
    }

    @RequestMapping(path = "/acs/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundry getAllElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.elementInterface.getAllElements(userDomain, userEmail);
    }


}
