package demo.acs.rest;

import demo.acs.data.ElementEntity;
import demo.acs.logic.ElementService;
import demo.acs.rest.boudanries.ElementBoundary;

import java.util.List;

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
    public ElementBoundary createNewElement(@PathVariable("managerDomain") String managerDomain, 
								    		@PathVariable("managerEmail") String managerEmail, 
								    		@RequestBody ElementBoundary element) {
        return this.elementService.create(managerDomain, managerEmail, element);
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
        this.elementService.update(managerDomain, managerEmail, elementDomain, elementId, element);
    }


    @RequestMapping(path = "/acs/elements/{userDomain}/{userEmail}/{elementDomain}/{elementId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ElementBoundary retrieveSpecificElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail,
            @PathVariable("elementDomain") String elementDomain,
            @PathVariable("elementId") String elementId) {
        return this.elementService.getSpecificElement(userDomain, userEmail, elementDomain, elementId);
    }

    @RequestMapping(path = "/acs/elements/{userDomain}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ElementBoundary> getAllElements(
            @PathVariable("userDomain") String userDomain,
            @PathVariable("userEmail") String userEmail) {
        return this.elementService.getAll(userDomain, userEmail);
    }
}
