package com.prueba.bcnc.inditex.utils;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.exception.PruebaInditexException;

@Component
public class ApiConnection {

	private static final String API_HOST = "https://jsonplaceholder.typicode.com/";

	private RestTemplate restTemplate;

	public ApiConnection() {
		restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(API_HOST));
	}

	public List<AlbumDto> albumsRequest() {
		ResponseEntity<String> response = restTemplate.getForEntity("/albums", String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(response.getBody(), new TypeReference<List<AlbumDto>>() {
			});
		} catch (JsonProcessingException e) {
			throw new PruebaInditexException(e.getMessage());
		}
	}

	public List<PhotoDto> photosRequest() {
		ResponseEntity<String> response = restTemplate.getForEntity("/photos", String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(response.getBody(), new TypeReference<List<PhotoDto>>() {
			});
		} catch (JsonProcessingException e) {
			throw new PruebaInditexException(e.getMessage());
		}
	}

}
