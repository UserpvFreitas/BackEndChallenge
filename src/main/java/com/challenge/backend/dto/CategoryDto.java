package com.challenge.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.challenge.backend.model.Category;

public class CategoryDto {
	private Long id;
	private String title;
	private String color;
	
		
	public CategoryDto(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.color = category.getColor();
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getColor() {
		return color;
	}
	
	public static List<CategoryDto> convert(List<Category> categories){
		return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
	}
	
}
