package com.prueba.bcnc.inditex.service;

import java.util.List;

import com.prueba.bcnc.inditex.model.Photo;

public interface PhotoService {

	public void savePhotos(List<Photo> photoList);

	public List<Photo> getPhotos();

}
