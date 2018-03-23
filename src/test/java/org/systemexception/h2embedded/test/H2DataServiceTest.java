package org.systemexception.h2embedded.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.repositories.DataRepository;
import org.systemexception.h2embedded.service.H2DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
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
	private final List<Data> dataList = new ArrayList<>();
	private final Long TEST_ID = 1L;

	@Before
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
	public void save_data() {
		sut = new H2DataService(dataRepository);
		Data newData = sut.create(data);

		assertTrue(newData.equals(data));
		verify(dataRepository).save(data);
	}

	@Test
	public void delete_existing_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);

		assertTrue(sut.delete(TEST_ID));
		verify(dataRepository).deleteByDataId(TEST_ID);
	}

	@Test
	public void delete_nonexisting_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(null);

		assertFalse(sut.delete(TEST_ID));
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
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);
		Data foundData = sut.findById(TEST_ID);

		assertTrue(Objects.equals(foundData.getDataId(), data.getDataId()));
		assertTrue(Objects.equals(foundData.getDataValue(), data.getDataValue()));
		verify(dataRepository).findByDataId(TEST_ID);
	}

	@Test
	public void update_existing_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(data);
		data = new Data();
		data.setDataId(TEST_ID);
		data.setDataValue(DataControllerTest.TEST_DATA);
		data.setDataTimestamp(null);
		assertTrue(sut.update(data));
		verify(dataRepository).update(TEST_ID, data.getDataValue());
		assertFalse(data.equals(sut.findById(TEST_ID)));
	}

	@Test
	public void update_nonexisting_data() {
		sut = new H2DataService(dataRepository);
		when(dataRepository.findByDataId(TEST_ID)).thenReturn(null);
		data = new Data();
		data.setDataId(TEST_ID);
		data.setDataValue(DataControllerTest.TEST_DATA);
		data.setDataTimestamp(null);
		assertFalse(sut.update(data));
		assertFalse(data.equals(sut.findById(TEST_ID)));
	}
}