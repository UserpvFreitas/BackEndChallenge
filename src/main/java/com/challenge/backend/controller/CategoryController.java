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

import com.challenge.backend.dto.CategoryDto;
import com.challenge.backend.form.CategoryForm;
import com.challenge.backend.form.UpdateCategoryForm;
import com.challenge.backend.model.Category;
import com.challenge.backend.repository.CategoryRepository;
import com.challenge.backend.repository.VideoRepository;

@RestController
@RequestMapping("/categories")
@ResponseBody
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping
	public List<CategoryDto> list(){
		List<Category> categories = categoryRepository.findAll();
		return CategoryDto.convert(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> detail(@PathVariable Long id) {
		Optional <Category> optional = categoryRepository.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(new CategoryDto(optional.get()));	
		}
		
		return ResponseEntity.notFound().build();		 
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDto> create(
		@RequestBody @Valid CategoryForm form,
		UriComponentsBuilder builder
		
	) {
		Category category = form.convert();
		categoryRepository.save(category);
		
		URI uri = builder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoryDto(category));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoryDto> update(
		@PathVariable Long id,
		@RequestBody @Valid UpdateCategoryForm form
	){
		Optional<Category> optional = categoryRepository.findById(id);
		
		if(optional.isPresent()) {
			Category category = form.update(id, categoryRepository);
			
			return ResponseEntity.ok(new CategoryDto(category));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Category> category = categoryRepository.findById(id);
		
		if(category.isPresent()) {
			videoRepository.findByCategoryId(id);
			
			categoryRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
