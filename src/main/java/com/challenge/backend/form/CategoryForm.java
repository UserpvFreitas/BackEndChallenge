package com.challenge.backend.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.challenge.backend.model.Category;

public class CategoryForm {
	private Long id;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	@NotNull @NotEmpty @Length(min = 5)
	private String color;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Category convert() {
		return new Category(this.getId(), this.getTitle(), this.getColor());
	}
}
