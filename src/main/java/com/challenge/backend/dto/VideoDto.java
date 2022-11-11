package com.challenge.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.challenge.backend.model.Video;

public class VideoDto {
	private Long id;	
	private String title;
	private String description;
	private String url;
	private String category;
		
	public VideoDto(Video video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.category = video.getCategory() == null ? "Livre" : video.getCategory().getTitle();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}
	
	public String getCategory() {
		return category;
	}

	public static List<VideoDto> convert(List<Video> videos){
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}
	
}
