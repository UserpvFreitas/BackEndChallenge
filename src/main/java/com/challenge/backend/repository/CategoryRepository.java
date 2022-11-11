package com.challenge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
