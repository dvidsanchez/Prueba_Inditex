package com.prueba.bcnc.inditex.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.repository.AlbumRepository;
import com.prueba.bcnc.inditex.repository.PhotoRepository;

@SpringBootTest
class PruebaInditexITTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@MockBean
	private AlbumRepository albumRepository;

	@MockBean
	private PhotoRepository photoRepository;

	@Test
	void saveToH2_Test_OK() throws Exception {
		when(albumRepository.saveAll(any())).thenReturn(new ArrayList<>());
		this.mockMvc.perform(get("/api/save_data")).andExpect(status().isNoContent());
	}

	@Test
	void getDataFromApi_Test_OK() throws Exception {
		this.mockMvc.perform(get("/api/api_data")).andExpect(status().isOk());
	}

	@Test
	void getDataFromDB_Test_OK() throws Exception {
		when(albumRepository.findAll()).thenReturn(getAlbumList());
		when(photoRepository.findAll()).thenReturn(getPhotoList());
		this.mockMvc.perform(get("/api/db_data")).andExpect(status().isOk());
	}

	@Test
	void getDataFromDB_Test_Error() throws Exception {
		when(albumRepository.findAll()).thenReturn(new ArrayList<>());
		when(photoRepository.findAll()).thenReturn(getPhotoList());
		this.mockMvc.perform(get("/api/db_data")).andExpect(status().isInternalServerError());
	}

	private List<Photo> getPhotoList() {
		List<Photo> photoList = new ArrayList<>();
		Photo photo = new Photo();
		photo.setId(1L);
		photo.setTitle("titulo");
		photo.setAlbumId(1L);
		photo.setThumbnailUrl("http://test.es?id=123");
		photo.setUrl("http://test.es?id=123");
		photoList.add(photo);
		return photoList;
	}

	private List<Album> getAlbumList() {
		List<Album> albumList = new ArrayList<>();
		Album album = new Album();
		album.setId(1L);
		album.setTitle("titulo");
		album.setUserId(1L);
		albumList.add(album);
		return albumList;
	}
}
