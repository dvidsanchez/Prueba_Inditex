package com.prueba.bcnc.inditex.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.service.AlbumService;
import com.prueba.bcnc.inditex.service.AlbumsPhotosService;
import com.prueba.bcnc.inditex.service.PhotoService;
import com.prueba.bcnc.inditex.utils.ApiConnection;

@RestController
@RequestMapping("/api")
public class PruebaInditexController {

	ApiConnection httpConnection;
	PhotoService photoService;
	AlbumService albumService;
	AlbumsPhotosService albumsPhotosService;

	public PruebaInditexController(ApiConnection httpConnection, PhotoService photoService, AlbumService albumService,
			AlbumsPhotosService albumsPhotosService) {
		this.httpConnection = httpConnection;
		this.photoService = photoService;
		this.albumService = albumService;
		this.albumsPhotosService = albumsPhotosService;
	}

	@GetMapping("/save_data")
	public ResponseEntity<Object> saveToH2() {
		albumService.getAlbumFromApiAndSave();
		photoService.getPhotoFromApiAndSave();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/api_data")
	public ResponseEntity<List<PhotoAlbumDto>> getDataFromAPI() {
		List<PhotoAlbumDto> responseList = albumsPhotosService.getDataFromApi();
		return ResponseEntity.ok(responseList);
	}

	@GetMapping("/db_data")
	public ResponseEntity<List<PhotoAlbumDto>> getDataFromDB() {
		List<PhotoAlbumDto> responseList = albumsPhotosService.getDataFromDB();
		return ResponseEntity.ok(responseList);
	}
}
