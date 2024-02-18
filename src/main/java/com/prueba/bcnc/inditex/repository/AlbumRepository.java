package com.prueba.bcnc.inditex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.bcnc.inditex.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
