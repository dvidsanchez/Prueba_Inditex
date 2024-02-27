package com.prueba.bcnc.inditex.service;

import java.util.List;

import com.prueba.bcnc.inditex.model.Album;

public interface AlbumService {

	public void saveAlbums(List<Album> albumList);

	public List<Album> getAlbums();

}
