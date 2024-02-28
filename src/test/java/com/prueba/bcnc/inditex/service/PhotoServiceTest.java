package com.prueba.bcnc.inditex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.repository.PhotoRepository;

@SpringBootTest
class PhotoServiceTest {

	@MockBean
	PhotoRepository photoRepository;

	@Autowired
	PhotoService photoService;

	@Test
	void saveAlbum_test_OK() {
		List<Photo> photoList = getPhotoList();
		photoService.savePhotos(photoList);
		Mockito.verify(photoRepository, times(1)).saveAll(photoList);
	}

	@Test
	void saveAlbum_test_Error() {
		assertThrows(PruebaInditexException.class, () -> {
			photoService.savePhotos(null);
		});
	}

	@Test
	void saveAlbum_test_Error_2() {
		List<Photo> photoList = new ArrayList<>();
		assertThrows(PruebaInditexException.class, () -> {
			photoService.savePhotos(photoList);
		});
	}

	@Test
	void getAlbum_test_OK() {
		when(photoRepository.findAll()).thenReturn(getPhotoList());
		List<Photo> photoList = photoService.getPhotos();
		assertEquals(1, photoList.size());
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

}
