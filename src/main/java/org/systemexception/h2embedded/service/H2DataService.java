package org.systemexception.h2embedded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

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
			dataRepository.delete(Integer.valueOf(id));
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
			dataRepository.update(foundData.getDataId(), foundData.getDataValue());
			return true;
		} else {
			return false;
		}
	}
}
