package com.jerry.jerryShop.category;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Category Service
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-14
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public List<CategoryDTO> getCategoryList(){
		List<CategoryDTO> results = this.categoryRepository.findAll().stream().map(CategoryDTO::of).collect(Collectors.toList());
		return results;
	}
	
	public List<CategoryDTO> getChildCategoryList(Long parent) {
		Optional<Category> _category = this.categoryRepository.findById(parent);
		List<CategoryDTO> results = this.categoryRepository.findByParent(_category.get()).stream().map(CategoryDTO::of).collect(Collectors.toList());
		return results;
	}
	
	public void create(Long depth, String name) {
		Category category = new Category();
		category.setDepth(depth);
		category.setName(name);
	}
	
}
