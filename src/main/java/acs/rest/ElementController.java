package acs.rest;

import acs.logic.ElementService;
import acs.logic.IncorrectInputExeption;
import acs.logic.InsafitiontInputExeption;
import acs.logic.UserNotFoundException;
import acs.rest.boudanries.ElementBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RestController
public class ElementController {

    private ElementService elementService;

    public ElementController(ElementService elementService) {
        super();
        this.elementService = elementService;
    }

    @Autowired
    public ElementController() {
	}
    
    @Autowired
    public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}
    
	// invoked by spring after singleton is created and after injections
	@PostConstruct
	public void init() {
		System.err.println("***** 2020b.ari.kuznicki");
	}
	
	// invoked by spring when it is shutting down gracefully
	@PreDestroy
	public void byeBye() {
		System.err.println("elementController is about to be destroyed...");
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
            @PathVariable("elementId") String elementId) throws Exception {
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
    
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleException (UserNotFoundException e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Element was not found":
						e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleException (InsafitiontInputExeption e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Insofficient input":
						e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, Object> handleException (IncorrectInputExeption e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Incorrect input":
						e.getMessage());
	}
	
}
