package com.challenge.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.challenge.backend.dto.VideoDto;
import com.challenge.backend.form.UpdateVideoForm;
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
	public List<VideoDto> list(){
		List<Video> video = videoRepository.findAll();
		return VideoDto.convert(video);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> detail(@PathVariable Long  id) {
		Optional<Video> video = videoRepository.findById(id);
		
		if(video.isPresent()) {
			return ResponseEntity.ok(new VideoDto(video.get())); 
		}
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> create(
		@RequestBody @Valid VideoForm form,
		UriComponentsBuilder uriBuilder
	){
		Video video = form.convert();
		videoRepository.save(video);
		
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateVideoForm form){
		Optional<Video> optional = videoRepository.findById(id);
		
		if(optional.isPresent()) {
			Video video = form.atualizar(id, videoRepository);
			return ResponseEntity.ok(new VideoDto(video)); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Video> optional = videoRepository.findById(id);
		
		if(optional.isPresent()) {			
			videoRepository.deleteById(id);
			return ResponseEntity.ok().build(); 
		}
		
		return ResponseEntity.notFound().build();		
	}
}
