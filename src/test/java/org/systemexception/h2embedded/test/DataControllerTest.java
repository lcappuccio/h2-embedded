package org.systemexception.h2embedded.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.systemexception.h2embedded.Application;
import org.systemexception.h2embedded.controller.DataController;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.service.DataService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author leo
 * @date 11/10/15 21:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
public class DataControllerTest {

	public final static String TEST_DATA = "TestData";
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	private final Data data = new Data();
	private DataService dataService;
	@InjectMocks
	private DataController dataController;
	private MockMvc sut;
	private final static String ENDPOINT = "/api/data/";
	private final Long existingId = 1L, nonExistingId = 99L;

	@Before
	public void setUp() {
		data.setDataId(existingId);
		data.setDataValue(TEST_DATA);
		List<Data> dataList = new ArrayList<>();
		dataList.add(data);
		dataService = mock(DataService.class);
		when(dataService.findAll()).thenReturn(dataList);
		when(dataService.findById(existingId)).thenReturn(data);
		when(dataService.findById(nonExistingId)).thenReturn(null);
		when(dataService.delete(existingId)).thenReturn(true);
		when(dataService.delete(nonExistingId)).thenReturn(false);
		when(dataService.create(any())).thenReturn(data);
		MockitoAnnotations.initMocks(this);
		sut = MockMvcBuilders.standaloneSetup(dataController)
				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	public void find_all_persons() throws Exception {
		sut.perform(MockMvcRequestBuilders.get(ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status()
				.is(HttpStatus.OK.value()));
		verify(dataService).findAll();
	}

	@Test
	public void find_single_data() throws Exception {
		dataService.create(data);
		sut.perform(MockMvcRequestBuilders.get(ENDPOINT + data.getDataId()).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(HttpStatus.FOUND.value()));
		verify(dataService).findById(existingId);
	}

	@Test
	public void dont_find_non_existing_data() throws Exception {
		sut.perform(MockMvcRequestBuilders.get(ENDPOINT + nonExistingId).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).findById(nonExistingId);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin_pwd", roles = {"ADMIN"})
	public void save_data() throws Exception {
		sut.perform(MockMvcRequestBuilders.post(ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.CREATED.value()));
		verify(dataService).create(any());
	}

	// TODO LC Re-enable with security
	@Test
	@Ignore
	@WithMockUser(username = "user", password = "user_pwd", roles = {"USER"})
	public void save_data_forbidden() throws Exception {
		sut.perform(MockMvcRequestBuilders.post(ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin_pwd", roles = {"ADMIN"})
	public void delete_existing_data() throws Exception {
		when(dataService.update(data)).thenReturn(true);
		sut.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + existingId)).andExpect(status()
				.is(HttpStatus.OK.value()));
		verify(dataService).delete(existingId);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin_pwd", roles = {"ADMIN"})
	public void delete_nonexisting_data() throws Exception {
		when(dataService.update(data)).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + nonExistingId)).andExpect(status()
				.is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).delete(nonExistingId);
	}

	// TODO LC Re-enable with security
	@Test
	@Ignore
	@WithMockUser(username = "user", password = "user_pwd", roles = {"USER"})
	public void delete_forbidden() throws Exception {
		when(dataService.update(data)).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + nonExistingId)).andExpect(status()
				.is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin_pwd", roles = {"ADMIN"})
	public void update_existing_data() throws Exception {
		when(dataService.update(any())).thenReturn(true);
		sut.perform(MockMvcRequestBuilders.put(ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.OK.value()));
		verify(dataService).update(any());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin_pwd", roles = {"ADMIN"})
	public void update_nonexisting_data() throws Exception {
		when(dataService.update(any())).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.put(ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).update(any());
	}

	// TODO LC Re-enable with security
	@Test
	@Ignore
	@WithMockUser(username = "user", password = "user_pwd", roles = {"USER"})
	public void update_forbidden() throws Exception {
		when(dataService.update(any())).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.put(ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	private String dataJson(Data data) {
		return "{\"name\":" + "\"" + data.getDataId() + "\"," +
				"\"lastName\":" + "\"" + data.getDataValue() + "\"}";
	}
}