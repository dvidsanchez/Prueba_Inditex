package com.prueba.bcnc.inditex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.repository.PhotoRepository;
import com.prueba.bcnc.inditex.service.PhotoService;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@Service
public class PhotoServiceImpl implements PhotoService {

	PhotoRepository photoRepository;

	ApiConnection utilsConnection;

	public PhotoServiceImpl(PhotoRepository photoRepository, ApiConnection utilsConnection) {
		this.photoRepository = photoRepository;
		this.utilsConnection = utilsConnection;
	}

	public void savePhotos(List<Photo> photoList) {
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se puede almacenar una lista vacía");
		}
		photoRepository.saveAll(photoList);
	}

	public List<Photo> getPhotos() {
		return photoRepository.findAll();
	}

	public List<PhotoDto> getPhotoFromApi() {
		return utilsConnection.photosRequest();
	}

	public void getPhotoFromApiAndSave() {
		List<PhotoDto> photoList = getPhotoFromApi();
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se han obtenido photos en la petición al API");
		}
		List<Photo> photoListSave = new ArrayList<>();
		for (PhotoDto photo : photoList) {
			Photo photoSave = new Photo();
			BeanUtils.copyProperties(photo, photoSave);
			photoListSave.add(photoSave);
		}
		savePhotos(photoListSave);
	}
}
