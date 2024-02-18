package com.prueba.bcnc.inditex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.model.Photo;
import com.prueba.bcnc.inditex.service.AlbumService;
import com.prueba.bcnc.inditex.service.AlbumsPhotosService;
import com.prueba.bcnc.inditex.service.PhotoService;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@Service
public class AlbumsPhotosServiceImpl implements AlbumsPhotosService {

	private AlbumService albumService;
	private PhotoService photoService;
	private ApiConnection apiConnection;

	public AlbumsPhotosServiceImpl(AlbumService albumService, PhotoService photoService, ApiConnection apiConnection) {
		this.albumService = albumService;
		this.photoService = photoService;
		this.apiConnection = apiConnection;
	}

	public List<PhotoAlbumDto> getDataFromApi() {
		List<AlbumDto> albumList = apiConnection.albumsRequest();
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se han obtenido albums en la petición al API");
		}
		List<PhotoDto> photoList = apiConnection.photosRequest();
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se han obtenido photos en la petición al API");
		}
		return assignPhotosToAlbum(albumList, photoList);
	}

	public List<PhotoAlbumDto> getDataFromDB() {
		List<Album> albumList = albumService.getAlbums();
		List<AlbumDto> albumListDto = new ArrayList<>();
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se han encontrado albums en la Base de Datos");
		}
		for (Album album : albumList) {
			AlbumDto albumDto = new AlbumDto();
			BeanUtils.copyProperties(album, albumDto);
			albumListDto.add(albumDto);
		}
		List<Photo> photoList = photoService.getPhotos();
		List<PhotoDto> photoListDto = new ArrayList<>();
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se han encontrado photos en la Base de Datos");
		}
		for (Photo photo : photoList) {
			PhotoDto photoDto = new PhotoDto();
			BeanUtils.copyProperties(photo, photoDto);
			photoListDto.add(photoDto);
		}
		return assignPhotosToAlbum(albumListDto, photoListDto);
	}

	private List<PhotoAlbumDto> assignPhotosToAlbum(List<AlbumDto> albumList, List<PhotoDto> photoList) {
		if (!CollectionUtils.isEmpty(albumList) && !CollectionUtils.isEmpty(photoList)) {
			Map<Long, PhotoAlbumDto> photoAlbumMap = new HashMap<>();
			for (AlbumDto album : albumList) {
				PhotoAlbumDto photoAlbumDto = new PhotoAlbumDto();
				BeanUtils.copyProperties(album, photoAlbumDto);
				photoAlbumMap.put(album.getId(), photoAlbumDto);
			}
			for (PhotoDto photo : photoList) {
				if (photoAlbumMap.containsKey(photo.getAlbumId())) {
					photoAlbumMap.get(photo.getAlbumId()).addPhotos(photo);
				}
			}
			if (!photoAlbumMap.isEmpty()) {
				return new ArrayList<>(photoAlbumMap.values());
			}
		}
		throw new PruebaInditexException("Se ha producido un error inesperado");
	}

}
