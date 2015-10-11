package org.systemexception.h2embedded.service;

import org.springframework.stereotype.Service;
import org.systemexception.h2embedded.domain.Data;

import java.util.List;
import java.util.Optional;

/**
 * @author leo
 * @date 11/10/15 16:42
 */
@Service
public interface DataService {

	/**
	 * @param data
	 * @return
	 */
	Data create(Data data);

	/**
	 * @param data
	 */
	void delete(Data data);

	/**
	 * @return
	 */
	List<Data> findAll();

	/**
	 * @param id
	 * @return
	 */
	Optional<Data> findById(Long id);

	/**
	 * @param data
	 * @return
	 */
	Data update(Data data);
}
