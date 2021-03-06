package org.systemexception.h2embedded.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author leo
 * @date 11/10/15 16:46
 */
@Service
@Transactional
public class H2DataService implements DataService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final DataRepository dataRepository;

	@Autowired
	public H2DataService(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public Data create(Data data) {
		logger.info("Create data: " + data.getDataValue());
		data.setDataTimestamp(java.sql.Timestamp.valueOf(getDate()));
		return dataRepository.save(data);
	}

	@Override
	public boolean delete(Long id) {
		Data foundData = dataRepository.findByDataId(id);
		if (foundData != null) {
			logger.info("Delete data: " + id);
			dataRepository.deleteByDataId(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Data> findAll() {
		return dataRepository.findAll();
	}

	@Override
	public Data findById(Long id) {
		return dataRepository.findByDataId(id);
	}

	@Override
	public boolean update(Data data) {
		Data foundData = dataRepository.findByDataId(data.getDataId());
		if (foundData != null) {
			logger.info("Update data: " + data.getDataId() + ", " + data.getDataValue());
			foundData.setDataValue(data.getDataValue());
			foundData.setDataTimestamp(java.sql.Timestamp.valueOf(getDate()));
			dataRepository.update(foundData.getDataId(), foundData.getDataValue());
			return true;
		} else {
			return false;
		}
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
