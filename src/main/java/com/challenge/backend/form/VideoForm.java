package com.challenge.backend.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.challenge.backend.model.Category;
import com.challenge.backend.model.Video;

public class VideoForm {
	private Long id;	
	
	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	@NotNull @NotEmpty @Length(min = 5)
	private String description;
	@NotNull @NotEmpty @Length(min = 5)
	private String url;
	
	private Category category;

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}


	public Video convert(){
		return new Video(this.title, this.description, this.url, this.category);
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}
}
