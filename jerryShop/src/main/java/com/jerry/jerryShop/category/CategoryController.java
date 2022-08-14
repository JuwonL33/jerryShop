package com.jerry.jerryShop.category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * JerryShop Category Controller
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-14
 * Copyright (C) 2022 by Jerry, All right reserved.
 */


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getCategoryList(){
		return ResponseEntity.ok(categoryService.getCategoryList());
	}
	
	@GetMapping("/list/child")
	public ResponseEntity<?> getChildCategoryList(Long parent){

		return ResponseEntity.ok(categoryService.getChildCategoryList(parent));
	}
}
