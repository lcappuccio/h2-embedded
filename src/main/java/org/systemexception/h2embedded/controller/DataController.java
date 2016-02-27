package org.systemexception.h2embedded.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.systemexception.h2embedded.constants.Endpoints;
import org.systemexception.h2embedded.constants.Parameters;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.service.DataService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * @author leo
 * @date 11/10/15 16:49
 */
@EnableSwagger2
@RestController
@RequestMapping(value = Endpoints.CONTEXT)
@Api(basePath = Endpoints.CONTEXT, value = "Data", description = "Data REST API")
public class DataController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final DataService dataService;

	@Autowired
	public DataController(DataService dataService) {
		this.dataService = dataService;
	}

	@ApiOperation(value = "Create data", notes = "Adds data to the database")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Data> create(@RequestBody @Valid Data data) {
		logger.info("Received CREATE: " + data.getDataValue());
		return new ResponseEntity<Data>(dataService.create(data), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete data", notes = "Deletes data from the database")
	@RequestMapping(value = Parameters.DATA_ID_API_PARAM, method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpStatus> delete(@PathVariable(Parameters.DATA_ID_PATH_VARIABLE) String id) {
		logger.info("Received DELETE: " + id);
		if (dataService.delete(Integer.valueOf(id))) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Find data by id", notes = "Use internal database id")
	@RequestMapping(value = Parameters.DATA_ID_API_PARAM, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	@ResponseBody
	public ResponseEntity<Data> findById(@PathVariable(Parameters.DATA_ID_PATH_VARIABLE) String id) {
		logger.info("Received Get: " + id);
		Data dataById = dataService.findById(Integer.valueOf(id));
		if(dataById != null) {
			return new ResponseEntity<Data>(dataById, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Data>(dataById, HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation(value = "List all data", notes = "Produces the full data list in database")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Data>> findAll() {
		logger.info("Received GET all persons");
		return new ResponseEntity<List<Data>>(dataService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update data", notes = "Unknown behaviour if id does not exist")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Data> update(@RequestBody @Valid Data data) {
		logger.info("Received UPDATE: " + data.getDataId() + ", " + data.getDataValue());
		if (dataService.update(data)) {
			return new ResponseEntity<Data>(dataService.findById(data.getDataId()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Data>(new Data(), HttpStatus.NOT_FOUND);
		}
	}
}
