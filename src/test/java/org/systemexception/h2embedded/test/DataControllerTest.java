package org.systemexception.h2embedded.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.systemexception.h2embedded.Application;
import org.systemexception.h2embedded.SecurityConfig;
import org.systemexception.h2embedded.controller.DataController;
import org.systemexception.h2embedded.domain.Data;
import org.systemexception.h2embedded.service.DataService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.systemexception.h2embedded.constants.Endpoints.CONTEXT;
import static org.systemexception.h2embedded.constants.Endpoints.PATH_SEPARATOR;

/**
 * @author leo
 * @date 11/10/15 21:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
public class DataControllerTest {

	private final Data data = new Data();
	final static String TEST_DATA = "TestData";
	private final Long existingId = 1L, nonExistingId = 99L;
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	@MockBean
	private DataService dataService;
	@Autowired
	private DataController dataController;
	private MockMvc sut;

	@Before
	public void setUp() {
		data.setDataId(existingId);
		data.setDataValue(TEST_DATA);
		List<Data> dataList = new ArrayList<>();
		dataList.add(data);
		when(dataService.findAll()).thenReturn(dataList);
		when(dataService.findById(existingId)).thenReturn(data);
		when(dataService.findById(nonExistingId)).thenReturn(null);
		when(dataService.delete(existingId)).thenReturn(true);
		when(dataService.delete(nonExistingId)).thenReturn(false);
		when(dataService.create(any())).thenReturn(data);
		sut = MockMvcBuilders.standaloneSetup(dataController)
				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	public void find_all_persons() throws Exception {
		sut.perform(MockMvcRequestBuilders.get(CONTEXT).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status()
				.is(HttpStatus.OK.value()));
		verify(dataService).findAll();
	}

	@Test
	public void find_single_data() throws Exception {
		dataService.create(data);
		sut.perform(MockMvcRequestBuilders.get(CONTEXT + PATH_SEPARATOR + data.getDataId())
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is(HttpStatus.FOUND.value()));
		verify(dataService).findById(existingId);
	}

	@Test
	public void dont_find_non_existing_data() throws Exception {
		sut.perform(MockMvcRequestBuilders.get(CONTEXT + PATH_SEPARATOR + nonExistingId)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).findById(nonExistingId);
	}

	@Test
	@WithMockUser(username = SecurityConfig.ADMIN_USER, password = SecurityConfig.ADMIN_PASSWORD,
			roles = {SecurityConfig.ADMIN_ROLE})
	public void save_data() throws Exception {
		sut.perform(MockMvcRequestBuilders.post(CONTEXT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.CREATED.value()));
		verify(dataService).create(any());
	}

	@Test
	@WithMockUser(username = SecurityConfig.USER_USER, password = SecurityConfig.USER_PASSWORD,
			roles = {SecurityConfig.USER_ROLE})
	public void save_data_forbidden() throws Exception {
		sut.perform(MockMvcRequestBuilders.post(CONTEXT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	@Test
	@WithMockUser(username = SecurityConfig.ADMIN_USER, password = SecurityConfig.ADMIN_PASSWORD,
			roles = {SecurityConfig.ADMIN_ROLE})
	public void delete_existing_data() throws Exception {
		when(dataService.update(data)).thenReturn(true);
		sut.perform(MockMvcRequestBuilders.delete(CONTEXT + PATH_SEPARATOR + existingId)).andExpect(status()
				.is(HttpStatus.OK.value()));
		verify(dataService).delete(existingId);
	}

	@Test
	@WithMockUser(username = SecurityConfig.ADMIN_USER, password = SecurityConfig.ADMIN_PASSWORD,
			roles = {SecurityConfig.ADMIN_ROLE})
	public void delete_nonexisting_data() throws Exception {
		when(dataService.update(data)).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.delete(CONTEXT + PATH_SEPARATOR + nonExistingId)).andExpect(status()
				.is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).delete(nonExistingId);
	}

	@Test
	@WithMockUser(username = SecurityConfig.USER_USER, password = SecurityConfig.USER_PASSWORD,
			roles = {SecurityConfig.USER_ROLE})
	public void delete_forbidden() throws Exception {
		when(dataService.update(data)).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.delete(CONTEXT + PATH_SEPARATOR + nonExistingId)).andExpect(status()
				.is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	@Test
	@WithMockUser(username = SecurityConfig.ADMIN_USER, password = SecurityConfig.ADMIN_PASSWORD,
			roles = {SecurityConfig.ADMIN_ROLE})
	public void update_existing_data() throws Exception {
		when(dataService.update(any())).thenReturn(true);
		sut.perform(MockMvcRequestBuilders.put(CONTEXT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.OK.value()));
		verify(dataService).update(any());
	}

	@Test
	@WithMockUser(username = SecurityConfig.ADMIN_USER, password = SecurityConfig.ADMIN_PASSWORD,
			roles = {SecurityConfig.ADMIN_ROLE})
	public void update_nonexisting_data() throws Exception {
		when(dataService.update(any())).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.put(CONTEXT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
		verify(dataService).update(any());
	}

	@Test
	@WithMockUser(username = SecurityConfig.USER_USER, password = SecurityConfig.USER_PASSWORD,
			roles = {SecurityConfig.USER_ROLE})
	public void update_forbidden() throws Exception {
		when(dataService.update(any())).thenReturn(false);
		sut.perform(MockMvcRequestBuilders.put(CONTEXT).contentType(MediaType.APPLICATION_JSON_VALUE).content
				(dataJson(data).getBytes())).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
		verifyNoMoreInteractions(dataService);
	}

	private String dataJson(Data data) {
		return "{\"name\":" + "\"" + data.getDataId() + "\"," +
				"\"lastName\":" + "\"" + data.getDataValue() + "\"}";
	}
}