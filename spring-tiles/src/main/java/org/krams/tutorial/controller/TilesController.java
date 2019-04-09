package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves pet related pages.
 * <p>
 * This utilizes the Apache Tiles 2 framework for the views
 */
@Controller
@RequestMapping("/tiles")
public class TilesController {

	protected static Logger logger = Logger.getLogger("controller");
	
	/**
	 * Handles and retrieves the main pets JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String getPetsPage() {
    	logger.debug("Received request to show pets page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to a Tiles template named "pets-tiles"
    	// This is defined in the tiles-definitions.xml
    	return "pet-tiles";
	}
    
    /**
     * Handles and retrieves the dogs JSP page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public String getDogPage() {
    	logger.debug("Received request to show dogs page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to a Tiles template named "dog-tiles"
    	// This is defined in the tiles-definitions.xml
    	return "dog-tiles";
	}
    
    /**
     * Handles and retrieves the dogs JSP page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/cats", method = RequestMethod.GET)
    public String getCatsPage() {
    	logger.debug("Received request to show cats page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to a Tiles template named "cat-tiles"
    	// This is defined in the tiles-definitions.xml
    	return "cat-tiles";
	}
}
