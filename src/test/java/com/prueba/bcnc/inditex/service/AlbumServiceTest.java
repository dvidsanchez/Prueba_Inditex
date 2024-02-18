package com.prueba.bcnc.inditex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.repository.AlbumRepository;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@SpringBootTest
class AlbumServiceTest {

	@MockBean
	AlbumRepository albumRepository;

	@MockBean
	ApiConnection apiConnection;

	@Autowired
	AlbumService albumService;

	@Test
	void saveAlbum_test_OK() {
		List<Album> albumList = getAlbumList();
		albumService.saveAlbums(albumList);
		Mockito.verify(albumRepository, times(1)).saveAll(albumList);
	}

	@Test
	void saveAlbum_test_Error() {
		assertThrows(PruebaInditexException.class, () -> {
			albumService.saveAlbums(null);
		});
	}

	@Test
	void saveAlbum_test_Error_2() {
		List<Album> albumList = new ArrayList<>();
		assertThrows(PruebaInditexException.class, () -> {
			albumService.saveAlbums(albumList);
		});
	}

	@Test
	void getAlbum_test_OK() {
		when(albumRepository.findAll()).thenReturn(getAlbumList());
		List<Album> albumList = albumService.getAlbums();
		assertEquals(1, albumList.size());
	}

	@Test
	void getAlbumApi_test_OK() {
		when(apiConnection.albumsRequest()).thenReturn(getAlbumDtoList());
		List<AlbumDto> albumList = albumService.getAlbumFromApi();
		assertEquals(1, albumList.size());
	}

	@Test
	void getAlbumApiAndSave_test_OK() {
		when(apiConnection.albumsRequest()).thenReturn(getAlbumDtoList());
		albumService.getAlbumFromApiAndSave();
		Mockito.verify(albumRepository, times(1)).saveAll(any());
	}

	@Test
	void getAlbumApiAndSave_test_Error() {
		when(apiConnection.albumsRequest()).thenReturn(new ArrayList<>());
		assertThrows(PruebaInditexException.class, () -> {
			albumService.getAlbumFromApiAndSave();
		});
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

	private List<AlbumDto> getAlbumDtoList() {
		List<AlbumDto> albumList = new ArrayList<>();
		AlbumDto album = new AlbumDto();
		album.setId(1L);
		album.setTitle("titulo");
		album.setUserId(1L);
		albumList.add(album);
		return albumList;
	}

}
