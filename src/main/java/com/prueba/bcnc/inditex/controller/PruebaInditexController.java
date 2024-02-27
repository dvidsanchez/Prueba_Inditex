package com.prueba.bcnc.inditex.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.service.AlbumsPhotosService;

@RestController
@RequestMapping("/api")
public class PruebaInditexController {

	private AlbumsPhotosService albumsPhotosService;

	public PruebaInditexController(AlbumsPhotosService albumsPhotosService) {
		this.albumsPhotosService = albumsPhotosService;
	}

	@GetMapping("/save_data")
	public ResponseEntity<List<PhotoAlbumDto>> saveToH2() {
		return ResponseEntity.ok(albumsPhotosService.saveDataOnDB());
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
