package com.prueba.bcnc.inditex.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class PhotoAlbumDto extends AlbumDto implements Serializable {

	private static final long serialVersionUID = -3659884790582872881L;

	private List<PhotoDto> photos;

	public List<PhotoDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoDto> photos) {
		this.photos = photos;
	}

	public void addPhotos(PhotoDto photo) {
		if (CollectionUtils.isEmpty(this.photos)) {
			this.photos = new ArrayList<>();
		}
		this.photos.add(photo);
	}

}
