package com.prueba.bcnc.inditex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;
import com.prueba.bcnc.inditex.mappers.AlbumPhotoMapper;
import com.prueba.bcnc.inditex.model.Album;
import com.prueba.bcnc.inditex.service.AlbumService;
import com.prueba.bcnc.inditex.service.AlbumsPhotosService;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@Service
public class AlbumsPhotosServiceImpl implements AlbumsPhotosService {

	private AlbumService albumService;
	private ApiConnection apiConnection;
	private AlbumPhotoMapper mapper;

	public AlbumsPhotosServiceImpl(AlbumService albumService, ApiConnection apiConnection, AlbumPhotoMapper mapper) {
		this.albumService = albumService;
		this.apiConnection = apiConnection;
		this.mapper = mapper;
	}

	public List<PhotoAlbumDto> getDataFromApi() {
		List<AlbumDto> albumList = apiConnection.albumsRequest();
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se han obtenido albums en la petición al API", HttpStatus.NOT_FOUND);
		}
		List<PhotoDto> photoList = apiConnection.photosRequest();
		if (CollectionUtils.isEmpty(photoList)) {
			throw new PruebaInditexException("No se han obtenido photos en la petición al API", HttpStatus.NOT_FOUND);
		}
		return assignPhotosToAlbum(albumList, photoList);
	}

	public List<PhotoAlbumDto> getDataFromDB() {
		List<Album> albumList = albumService.getAlbums();
		if (CollectionUtils.isEmpty(albumList)) {
			throw new PruebaInditexException("No se han encontrado datos en la Base de Datos", HttpStatus.NOT_FOUND);
		}
		return mapper.albumListToPhotoAlbumDtoList(albumList);
	}

	public List<PhotoAlbumDto> saveDataOnDB() {
		List<PhotoAlbumDto> photoAlbumDtoList = getDataFromApi();
		albumService.saveAlbums(mapper.photoAlbumDtoListToalbumList(photoAlbumDtoList));
		return photoAlbumDtoList;
	}

	private List<PhotoAlbumDto> assignPhotosToAlbum(List<AlbumDto> albumList, List<PhotoDto> photoList) {
		if (!CollectionUtils.isEmpty(albumList) && !CollectionUtils.isEmpty(photoList)) {
			Map<Long, PhotoAlbumDto> photoAlbumMap = new HashMap<>();
			for (AlbumDto album : albumList) {
				PhotoAlbumDto photoAlbumDto = mapper.albumDtoToPhotoAlbumDto(album);
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
		throw new PruebaInditexException("Se ha producido un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
