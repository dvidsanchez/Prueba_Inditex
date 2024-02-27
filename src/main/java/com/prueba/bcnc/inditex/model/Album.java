package com.prueba.bcnc.inditex.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@Entity
public class Album {

	@Id
	@Column
	private Long id;
	@Column
	private Long userId;
	@Column
	private String title;
	@OneToMany(mappedBy = "albumId", cascade = CascadeType.ALL)
	private List<Photo> photos;

}
