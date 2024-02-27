package com.prueba.bcnc.inditex.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.bcnc.inditex.dto.AlbumDto;
import com.prueba.bcnc.inditex.dto.PhotoAlbumDto;
import com.prueba.bcnc.inditex.model.Album;

@Mapper(componentModel = "spring")
public interface AlbumPhotoMapper {

	List<PhotoAlbumDto> albumListToPhotoAlbumDtoList(List<Album> albumList);

	List<Album> photoAlbumDtoListToalbumList(List<PhotoAlbumDto> photoAlbumList);

	PhotoAlbumDto albumToPhotoAlbumDto(Album album);

	Album photoAlbumDtoToalbum(PhotoAlbumDto photoAlbum);

	@Mapping(target = "photos", ignore = true)
	PhotoAlbumDto albumDtoToPhotoAlbumDto(AlbumDto album);
}
