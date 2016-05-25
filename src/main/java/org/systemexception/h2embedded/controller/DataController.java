package org.systemexception.h2embedded.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.systemexception.h2embedded.constants.Endpoints;
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

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Data> create(@RequestBody @Valid Data data) {
		logger.info("Received CREATE: " + data.getDataValue());
		Data createdData = dataService.create(data);
		logger.info("Created: " + createdData.getDataId());
		return new ResponseEntity<>(createdData, HttpStatus.CREATED);
	}

	@RequestMapping(value = Endpoints.DATA_ID, method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		logger.info("Received DELETE: " + id);
		if (dataService.delete(Long.valueOf(id))) {
			logger.info("Deleted id: " + id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			logNotFound(id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = Endpoints.DATA_ID, method = RequestMethod.GET)
	public ResponseEntity<Data> findById(@PathVariable("id") String id) {
		logger.info("Received Get: " + id);
		Data dataById = dataService.findById(Long.valueOf(id));
		if (dataById != null) {
			logger.info("Found: " + id);
			return new ResponseEntity<>(dataById, HttpStatus.FOUND);
		} else {
			logNotFound(id);
			return new ResponseEntity<>(dataById, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Data>> findAll() {
		logger.info("Received GET all persons");
		List<Data> dataList = dataService.findAll();
		logger.info("Total data: " + dataList.size());
		return new ResponseEntity<>(dataList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Data> update(@RequestBody @Valid Data data) {
		logger.info("Received UPDATE: " + data.getDataId() + ", " + data.getDataValue());
		if (dataService.update(data)) {
			logger.info("Updated: " + data.getDataId());
			return new ResponseEntity<>(dataService.findById(data.getDataId()), HttpStatus.OK);
		} else {
			logNotFound(String.valueOf(data.getDataId()));
			return new ResponseEntity<>(new Data(), HttpStatus.NOT_FOUND);
		}
	}

	private void logNotFound(String dataId) {
		logger.info("Not found: " + dataId);
	}
}
