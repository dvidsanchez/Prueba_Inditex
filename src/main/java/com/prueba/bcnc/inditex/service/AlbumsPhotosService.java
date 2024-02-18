package com.prueba.bcnc.inditex.service;

import java.util.List;

import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;

public interface AlbumsPhotosService {

	public List<PhotoAlbumDto> getDataFromApi();

	public List<PhotoAlbumDto> getDataFromDB();
}
