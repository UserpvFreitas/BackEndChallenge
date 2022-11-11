package com.challenge.backend.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.challenge.backend.model.Category;
import com.challenge.backend.repository.CategoryRepository;

public class UpdateCategoryForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	@NotNull @NotEmpty @Length(min = 5)
	private String color;
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setColor(String color) {
		this.color = color;
	}
			
	public String getTitle() {
		return title;
	}
	public String getColor() {
		return color;
	}
	
	public Category update(Long id, CategoryRepository categoryRepository) {
		Category category = categoryRepository.getReferenceById(id);
		category.setTitle(this.title);
		category.setColor(this.color);
		return category;
	}
}
