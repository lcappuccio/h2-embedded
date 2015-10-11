package org.systemexception.h2embedded.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.systemexception.h2embedded.service.DataService;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

/**
 * @author leo
 * @date 11/10/15 16:49
 */
@RestController
@RequestMapping(value = "/api/data")
public class DataController {

	private final static Logger logger = LoggerImpl.getFor(DataController.class);
	private final DataService dataService;

	@Autowired
	public DataController(DataService dataService) {
		this.dataService = dataService;
	}
}
