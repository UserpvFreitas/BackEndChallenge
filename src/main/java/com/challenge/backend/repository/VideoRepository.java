package com.challenge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.backend.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

}