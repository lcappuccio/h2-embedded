package org.systemexception.h2embedded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.systemexception.h2embedded.domain.Data;

import java.util.List;
import java.util.Optional;

/**
 * @author leo
 * @date 11/10/15 16:47
 */
@Component
public interface DataRepository extends JpaRepository<Data, Integer> {

	/**
	 * @param data
	 * @return
	 */
	Data save(Data data);

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
	Optional<Data> findById(Integer id);
}
