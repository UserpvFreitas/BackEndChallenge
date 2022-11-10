package com.challenge.backend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.challenge.backend.dto.VideoDto;
import com.challenge.backend.form.VideoForm;
import com.challenge.backend.model.Video;
import com.challenge.backend.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
@ResponseBody
public class VideoController{ 
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	public List<VideoDto> lista(){
		List<Video> video = videoRepository.findAll();
		return VideoDto.converter(video);
	}	
	
	@PostMapping
	public ResponseEntity<VideoDto> cadastrar(
		@RequestBody @Valid VideoForm form,
		UriComponentsBuilder uriBuilder
	){
		Video video = form.converter();
		videoRepository.save(video);
		
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));		
	}
	
	@GetMapping("/{id}")
	public VideoDto detalhe(@PathVariable Long  id) {
		Video video = videoRepository.getReferenceById(id);
		return new VideoDto(video);
	}
}
