package org.systemexception.h2embedded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repository.DataRepository;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.util.List;
import java.util.Optional;

/**
 * @author leo
 * @date 11/10/15 16:46
 */
@Component
@Service
public class H2DataService implements DataService {

	private static final Logger logger = LoggerImpl.getFor(H2DataService.class);
	private final DataRepository dataRepository;

	@Autowired
	public H2DataService(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public Data create(Data data) {
		return null;
	}

	@Override
	public void delete(Data data) {
	}

	@Override
	public List<Data> findAll() {
		return null;
	}

	@Override
	public Optional<Data> findById(Long id) {
		return null;
	}

	@Override
	public Data update(Data data) {
		return null;
	}
}
