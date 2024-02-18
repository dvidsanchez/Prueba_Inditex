package com.prueba.bcnc.inditex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.repository.AlbumRepository;
import com.prueba.bcnc.inditex.service.AlbumService;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@Service
public class AlbumServiceImpl implements AlbumService {

	AlbumRepository albumRepository;

	ApiConnection utilsConnection;

	public AlbumServiceImpl(AlbumRepository albumRepository, ApiConnection utilsConnection) {
		this.albumRepository = albumRepository;
		this.utilsConnection = utilsConnection;
	}

	public void saveAlbums(List<Album> albumList) {
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se puede almacenar una lista vacía");
		}
		albumRepository.saveAll(albumList);
	}

	public List<Album> getAlbums() {
		return albumRepository.findAll();
	}

	public List<AlbumDto> getAlbumFromApi() {
		return utilsConnection.albumsRequest();
	}

	public void getAlbumFromApiAndSave() {
		List<AlbumDto> albumList = getAlbumFromApi();
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se han obtenido albums en la petición al API");
		}
		List<Album> albumListSave = new ArrayList<>();
		for (AlbumDto album : albumList) {
			Album albumSave = new Album();
			BeanUtils.copyProperties(album, albumSave);
			albumListSave.add(albumSave);
		}
		saveAlbums(albumListSave);
	}

}
