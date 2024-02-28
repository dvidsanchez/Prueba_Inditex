package com.prueba.bcnc.inditex.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class PruebaInditexITTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	@Order(1)
	void getDataFromDB_Test_Error() throws Exception {
		this.mockMvc.perform(get("/api/db_data")).andExpect(status().isNotFound());
	}

	@Test
	void getDataFromApi_Test_OK() throws Exception {
		MvcResult response = this.mockMvc.perform(get("/api/api_data")).andExpect(status().isOk()).andReturn();

		ObjectMapper objectMapper = new ObjectMapper();
		List<PhotoAlbumDto> resultList = objectMapper.readValue(response.getResponse().getContentAsString(),
				new TypeReference<List<PhotoAlbumDto>>() {
				});

		assertFalse(CollectionUtils.isEmpty(resultList));
		assertFalse(CollectionUtils.isEmpty(resultList.get(0).getPhotos()));
		assertEquals(resultList.get(0).getId(), resultList.get(0).getPhotos().get(0).getAlbumId());
	}

	@Test
	void saveToH2_Test_OK() throws Exception {
		MvcResult response = this.mockMvc.perform(get("/api/save_data")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].photos").isArray())
				.andExpect(jsonPath("$[0].id").exists()).andExpect(jsonPath("$[0].photos").isNotEmpty())
				.andExpect(jsonPath("$[0].photos[0].albumId").exists()).andReturn();

		ObjectMapper objectMapper = new ObjectMapper();
		List<PhotoAlbumDto> resultList = objectMapper.readValue(response.getResponse().getContentAsString(),
				new TypeReference<List<PhotoAlbumDto>>() {
				});

		assertFalse(CollectionUtils.isEmpty(resultList));
		assertFalse(CollectionUtils.isEmpty(resultList.get(0).getPhotos()));
		assertEquals(resultList.get(0).getId(), resultList.get(0).getPhotos().get(0).getAlbumId());
	}

	@Test
	void getDataFromDB_Test_OK() throws Exception {
		MvcResult response = this.mockMvc.perform(get("/api/db_data")).andExpect(status().isOk()).andReturn();

		ObjectMapper objectMapper = new ObjectMapper();
		List<PhotoAlbumDto> resultList = objectMapper.readValue(response.getResponse().getContentAsString(),
				new TypeReference<List<PhotoAlbumDto>>() {
				});

		assertFalse(CollectionUtils.isEmpty(resultList));
		assertFalse(CollectionUtils.isEmpty(resultList.get(0).getPhotos()));
		assertEquals(resultList.get(0).getId(), resultList.get(0).getPhotos().get(0).getAlbumId());
	}

}
