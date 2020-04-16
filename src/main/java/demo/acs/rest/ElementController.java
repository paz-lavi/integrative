package demo.acs.rest;

import demo.acs.data.Element;
import demo.acs.logic.ElementService;
import demo.acs.rest.boudanries.ElementBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElementController {

    private ElementService elementService;

    @Autowired
    public ElementController(ElementService elementService) {
        super();
        this.elementService = elementService;
    }


    @RequestMapping(path = "/acs/elements/{managerDomain}/{managerEmail}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundary createNewElement(@RequestBody Element element) {
        return this.elementService.createNewElement(element);
    }

    @RequestMapping(path = "/acs/elements/{managerDomain}/{managerEmail}/{elementDomain}/{elementId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateElementDetails(
            @PathVariable("managerDomain") String managerDomain,
            @PathVariable("managerEmail") String managerEmail,
            @PathVariable("elementDomain") String elementDomain,
            @PathVariable("elementId") String elementId,
            @RequestBody ElementBoundary element) {
        this.elementService.updateElementDetails(managerDomain, managerEmail, elementDomain, elementId, element);
    }


    @RequestMapping(path = "/acs/elements/{userDomain}/{userEmail}/{elementDomain}/{elementId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundary retrieveSpecificElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @PathVariable("elementDomain") String elementDomain,
            @PathVariable("elementId") String elementId) {
        return this.elementService.retrieveSpecificElements(userDomain, userEmail, elementDomain, elementId);
    }

    @RequestMapping(path = "/acs/elements/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundary getAllElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.elementService.getAllElements(userDomain, userEmail);
    }


}
