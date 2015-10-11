package org.systemexception.h2embedded.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.service.DataService;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * @author leo
 * @date 11/10/15 16:49
 */
@EnableSwagger2
@RestController
@RequestMapping(value = "/api/data")
@Api(basePath = "/api/data", value = "Data", description = "Data REST API")
public class DataController {

	private final static Logger logger = LoggerImpl.getFor(DataController.class);
	private final DataService dataService;

	@Autowired
	public DataController(DataService dataService) {
		this.dataService = dataService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType
			.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Create data", notes = "Adds data to the database")
	Data create(@RequestBody @Valid Data data) {
		logger.info("Received CREATE: " + data.getDataValue());
		return dataService.create(data);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Delete data", notes = "Deletes data from the database")
	void delete(@PathVariable("id") String id) {
		logger.info("Received DELETE: " + id);
		dataService.delete(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Find data by id", notes = "Use internal database id")
	Data findById(@PathVariable("id") String id) {
		logger.info("Received Get: " + id);
		return dataService.findById(Integer.valueOf(id));
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List all data", notes = "Produces the full data list in database")
	List<Data> findAll() {
		logger.info("Received GET all persons");
		return dataService.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update data", notes = "Unknown behaviour if id does not exist")
	void update(@RequestBody @Valid Data data) {
		logger.info("Received UPDATE: " + data.getDataId() + ", " + data.getDataValue());
		dataService.update(data);
	}
}