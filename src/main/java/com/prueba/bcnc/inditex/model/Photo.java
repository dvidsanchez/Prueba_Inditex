package com.prueba.bcnc.inditex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@Entity
public class Photo {

	@Id
	@Column
	private Long id;
	@Column
	private Long albumId;
	@Column
	private String title;
	@Column
	private String url;
	@Column
	private String thumbnailUrl;

}
