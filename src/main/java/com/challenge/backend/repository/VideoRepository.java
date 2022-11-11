package com.challenge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.backend.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
	List<Video> findByCategoryId(Long id);
}