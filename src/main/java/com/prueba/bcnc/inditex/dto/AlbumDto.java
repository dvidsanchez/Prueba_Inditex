package com.prueba.bcnc.inditex.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class AlbumDto implements Serializable {

	private static final long serialVersionUID = 525410751195649159L;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("userId")
	private Long userId;
	@JsonProperty("title")
	private String title;

}
