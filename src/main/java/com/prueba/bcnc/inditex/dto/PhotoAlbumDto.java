package com.prueba.bcnc.inditex.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@Data
@Generated
@EqualsAndHashCode(callSuper = true)
public class PhotoAlbumDto extends AlbumDto implements Serializable {

	private static final long serialVersionUID = -3659884790582872881L;

	@JsonProperty("photos")
	private List<PhotoDto> photos;

	public void addPhotos(PhotoDto photo) {
		if (CollectionUtils.isEmpty(this.photos)) {
			this.photos = new ArrayList<>();
		}
		this.photos.add(photo);
	}

}
