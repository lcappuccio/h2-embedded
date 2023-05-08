package org.systemexception.h2embedded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.systemexception.h2embedded.controller.DataControllerTest;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author leo
 * @date 11/10/15 23:31
 */
class H2DataServiceTest {

	private H2DataService sut;
	private DataRepository dataRepository;
	private Data data;
	private final List<Data> dataList = new ArrayList<>();
	private final Long TEST_ID = 1L;

	@BeforeEach
	public void setUp() {
		data = new Data();
		data.setDataId(TEST_ID);
		data.setDataValue(DataControllerTest.TEST_DATA);
		dataList.add(data);
		dataRepository = mock(DataRepository.class);
		when(dataRepository.save(data)).thenReturn(data);
		when(dataRepository.findAll()).thenReturn(dataList);
	}

	@Test
	void save_data() {
		sut = new H2DataService(dataRepository);
		Data newData = sut.create(data);

		assertEquals(newData, data);
		verify(dataRepository).save(data);
	}

	@Test
	void delete_existing_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);

		assertTrue(sut.delete(TEST_ID));
		verify(dataRepository).deleteByDataId(TEST_ID);
	}

	@Test
	void delete_nonexisting_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(null);

		assertFalse(sut.delete(TEST_ID));
	}

	@Test
	void find_all_data() {
		sut = new H2DataService((dataRepository));
		List<Data> datas = sut.findAll();

		assertEquals(datas.size(), dataList.size());
		verify(dataRepository).findAll();
	}

	@Test
	void find_single_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);
		Data foundData = sut.findById(TEST_ID);

		assertEquals(foundData.getDataId(), data.getDataId());
		assertEquals(foundData.getDataValue(), data.getDataValue());
		verify(dataRepository).findByDataId(TEST_ID);
	}

	@Test
	void update_existing_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);
		data = new Data();
		data.setDataId(TEST_ID);
		data.setDataValue(DataControllerTest.TEST_DATA);
		data.setDataTimestamp(null);
		assertTrue(sut.update(data));
		verify(dataRepository).update(TEST_ID, data.getDataValue());
		assertNotEquals(data, sut.findById(TEST_ID));
	}

	@Test
	void update_nonexisting_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(null);
		data = new Data();
		data.setDataId(TEST_ID);
		data.setDataValue(DataControllerTest.TEST_DATA);
		data.setDataTimestamp(null);
		assertFalse(sut.update(data));
		assertNotEquals(data, sut.findById(TEST_ID));
	}
}