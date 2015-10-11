package org.systemexception.h2embedded.service;

import org.springframework.stereotype.Service;
import org.systemexception.h2embedded.domain.Data;

import java.util.List;

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
	 * @param id
	 */
	boolean delete(Integer id);

	/**
	 * @return
	 */
	List<Data> findAll();

	/**
	 * @param id
	 * @return
	 */
	Data findById(Integer id);

	/**
	 * @param data
	 * @return
	 */
	boolean update(Data data);
}
