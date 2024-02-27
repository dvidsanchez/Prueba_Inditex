package com.prueba.bcnc.inditex.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.model.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

	AlbumDto albumToAlbumDto(Album album);

	List<AlbumDto> albumListToAlbumDtoList(List<Album> albumList);

	@Mapping(target = "photos", ignore = true)
	Album albumDtoToAlbum(AlbumDto album);

	List<Album> albumDtoListToAlbumList(List<AlbumDto> albumList);

}
