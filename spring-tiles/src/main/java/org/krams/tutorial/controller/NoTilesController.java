package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves pet related pages
 * <p>
 * This utilizes just the standard JSP pages
 */
@Controller
@RequestMapping("/notiles")
public class NoTilesController {

	protected static Logger logger = Logger.getLogger("controller");
	
    /**
     * Handles and retrieves the dogs JSP page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String getNoLayoutMain() {
    	logger.debug("Received request to show cats page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/notiles/pets.jsp
    	return "notiles/pets";
	}
    
    /**
     * Handles and retrieves the dogs JSP page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public String getNoLayoutDogs() {
    	logger.debug("Received request to show cats page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/notiles/dogs.jsp
    	return "notiles/dogs";
	}
    
    /**
     * Handles and retrieves the dogs JSP page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/cats", method = RequestMethod.GET)
    public String getNoLayoutCats() {
    	logger.debug("Received request to show cats page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/notiles/cats.jsp
    	return "notiles/cats";
	}
}
