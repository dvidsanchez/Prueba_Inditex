package com.prueba.bcnc.inditex.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoDto;

@SpringBootTest
class ApiConnectionTest {

	@Autowired
	ApiConnection apiConnection;

	@Test
	void albumsRequest_Test_OK() {
		List<AlbumDto> responseList = apiConnection.albumsRequest();
		assertEquals(false, CollectionUtils.isEmpty(responseList));
	}

	@Test
	void photoRequest_Test_OK() {
		List<PhotoDto> responseList = apiConnection.photosRequest();
		assertEquals(false, CollectionUtils.isEmpty(responseList));
	}

}
