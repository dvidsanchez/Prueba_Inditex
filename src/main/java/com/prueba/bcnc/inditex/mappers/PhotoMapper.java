package com.prueba.bcnc.inditex.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.prueba.bcnc.inditex.dto.PhotoDto;
import com.prueba.bcnc.inditex.model.Photo;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

	PhotoDto photoToPhotoDto(Photo photo);

	List<PhotoDto> photoListToPhotoDtoList(List<Photo> photoList);

	Photo photoDtoToPhotoDto(PhotoDto photo);

	List<Photo> photoDtoListToPhotoList(List<PhotoDto> photoList);

}
