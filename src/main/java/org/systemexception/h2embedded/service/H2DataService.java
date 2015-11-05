package org.systemexception.h2embedded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author leo
 * @date 11/10/15 16:46
 */
@Component
@Service
@Transactional
public class H2DataService implements DataService {

	private static final Logger logger = LoggerImpl.getFor(H2DataService.class);
	private final DataRepository dataRepository;

	@Autowired
	public H2DataService(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public Data create(Data data) {
		logger.info("Create data: " + data.getDataValue());
		return dataRepository.save(data);
	}

	@Override
	public boolean delete(Integer id) {
		Data foundData = dataRepository.findOne(id);
		if (foundData != null) {
			logger.info("Delete data: " + id);
			dataRepository.delete(id);
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
	public Data findById(Integer id) {
		return dataRepository.findOne(id);
	}

	@Override
	public boolean update(Data data) {
		Data foundData = dataRepository.findOne(data.getDataId());
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
