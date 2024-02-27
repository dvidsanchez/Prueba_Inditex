package com.prueba.bcnc.inditex.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.repository.PhotoRepository;
import com.prueba.bcnc.inditex.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	PhotoRepository photoRepository;

	public PhotoServiceImpl(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}

	public void savePhotos(List<Photo> photoList) {
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se puede almacenar una lista vacía", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		photoRepository.saveAll(photoList);
	}

	public List<Photo> getPhotos() {
		return photoRepository.findAll();
	}

}
