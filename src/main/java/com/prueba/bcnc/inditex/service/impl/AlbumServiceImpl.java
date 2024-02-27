package com.prueba.bcnc.inditex.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.repository.AlbumRepository;
import com.prueba.bcnc.inditex.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

	private AlbumRepository albumRepository;

	public AlbumServiceImpl(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	public void saveAlbums(List<Album> albumList) {
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se puede almacenar una lista vacía", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		albumRepository.saveAll(albumList);
	}

	public List<Album> getAlbums() {
		return albumRepository.findAll();
	}

}
