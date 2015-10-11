package org.systemexception.h2embedded.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;
import org.systemexception.h2embedded.service.H2DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author leo
 * @date 11/10/15 23:31
 */
public class H2DataServiceTest {

	private H2DataService sut;
	private DataRepository dataRepository;
	private Data data;
	private List<Data> dataList = new ArrayList<>();

	@Before
	public void setUp() {
		data = new Data();
		data.setDataValue("TestData");
		dataList.add(data);
		dataRepository = mock(DataRepository.class);
		when(dataRepository.save(data)).thenReturn(data);
		when(dataRepository.findAll()).thenReturn(dataList);
		when(dataRepository.findOne(1)).thenReturn(data);
	}

	@Test
	public void save_data() {
		sut = new H2DataService(dataRepository);
		Data newData = sut.create(data);

		assertTrue(newData.equals(data));
		verify(dataRepository).save(data);
	}

	@Test
	public void delete_data() {
		sut = new H2DataService(dataRepository);
		sut.delete(1);

		verify(dataRepository).delete(1);
	}

	@Test
	public void find_all_data() {
		sut = new H2DataService((dataRepository));
		List<Data> datas = sut.findAll();

		assertTrue(datas.size() == dataList.size());
		verify(dataRepository).findAll();
	}

	@Test
	public void find_single_data() {
		sut = new H2DataService(dataRepository);
		Data foundData = sut.findById(1);

		assertTrue(Objects.equals(foundData.getDataId(), data.getDataId()));
		assertTrue(Objects.equals(foundData.getDataValue(), data.getDataValue()));
		verify(dataRepository).findOne(1);
	}

	@Test
	public void update_data() {
		sut = new H2DataService(dataRepository);
		data.setDataId(1);
		sut.update(data);

		verify(dataRepository).update(1, data.getDataValue());
	}
}