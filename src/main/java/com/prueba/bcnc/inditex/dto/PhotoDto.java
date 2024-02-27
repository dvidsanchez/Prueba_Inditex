package com.prueba.bcnc.inditex.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class PhotoDto implements Serializable {

	private static final long serialVersionUID = 5742398739489832189L;

	private Long id;
	private Long albumId;
	private String title;
	private String url;
	private String thumbnailUrl;

}
