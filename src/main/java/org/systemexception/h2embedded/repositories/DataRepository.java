package org.systemexception.h2embedded.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.systemexception.h2embedded.domain.Data;

import java.util.List;

/**
 * @author leo
 * @date 11/10/15 16:47
 */
public interface DataRepository extends CrudRepository<Data, Integer> {

	/**
	 * @param data
	 * @return
	 */
	Data save(Data data);

	/**
	 * @param id
	 */
	@Modifying
	void delete(Integer id);

	/**
	 * @return
	 */
	List<Data> findAll();

	/**
	 * @param id
	 * @return
	 */
	Data findOne(Integer id);

	/**
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Data d set d.dataValue=:dataValue where d.dataId=:id")
	void update(@Param("id") int id, @Param("dataValue") String dataValue);
}
