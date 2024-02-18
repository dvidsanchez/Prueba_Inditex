package com.prueba.bcnc.inditex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@SpringBootTest
class AlbumsPhotosServiceTest {

	@MockBean
	ApiConnection apiConnection;

	@MockBean
	AlbumService albumService;

	@MockBean
	PhotoService photoService;

	@Autowired
	AlbumsPhotosService albumsPhotoService;

	@Test
	void getDataFromApi_Test_OK() {
		when(apiConnection.albumsRequest()).thenReturn(getAlbumDtoList());
		when(apiConnection.photosRequest()).thenReturn(getPhotoDtoList());
		List<PhotoAlbumDto> response = albumsPhotoService.getDataFromApi();
		assertEquals(1, response.size());
		assertEquals("titulo", response.get(0).getTitle());
		assertEquals(1, response.get(0).getPhotos().size());
		assertEquals("http://test.es?id=123", response.get(0).getPhotos().get(0).getUrl());
	}

	@Test
	void getDataFromApi_Test_Error_1() {
		when(apiConnection.albumsRequest()).thenReturn(new ArrayList<>());
		assertThrows(PruebaInditexException.class, () -> {
			albumsPhotoService.getDataFromApi();
		});
	}

	@Test
	void getDataFromApi_Test_Error_2() {
		when(apiConnection.albumsRequest()).thenReturn(getAlbumDtoList());
		when(apiConnection.photosRequest()).thenReturn(new ArrayList<>());
		assertThrows(PruebaInditexException.class, () -> {
			albumsPhotoService.getDataFromApi();
		});
	}

	@Test
	void getDataFromDB_Test_OK() {
		when(albumService.getAlbums()).thenReturn(getAlbumList());
		when(photoService.getPhotos()).thenReturn(getPhotoList());
		List<PhotoAlbumDto> response = albumsPhotoService.getDataFromDB();
		assertEquals(1, response.size());
		assertEquals("titulo", response.get(0).getTitle());
		assertEquals(1, response.get(0).getPhotos().size());
		assertEquals("http://test.es?id=123", response.get(0).getPhotos().get(0).getUrl());
	}

	@Test
	void getDataFromDB_Test_Error_1() {
		when(albumService.getAlbums()).thenReturn(new ArrayList<>());
		assertThrows(PruebaInditexException.class, () -> {
			albumsPhotoService.getDataFromDB();
		});
	}

	@Test
	void getDataFromDB_Test_Error_2() {
		when(albumService.getAlbums()).thenReturn(getAlbumList());
		when(photoService.getPhotos()).thenReturn(new ArrayList<>());
		assertThrows(PruebaInditexException.class, () -> {
			albumsPhotoService.getDataFromDB();
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

	private List<PhotoDto> getPhotoDtoList() {
		List<PhotoDto> photoList = new ArrayList<>();
		PhotoDto photo = new PhotoDto();
		photo.setId(1L);
		photo.setTitle("titulo");
		photo.setAlbumId(1L);
		photo.setThumbnailUrl("http://test.es?id=123");
		photo.setUrl("http://test.es?id=123");
		photoList.add(photo);
		return photoList;
	}
}
