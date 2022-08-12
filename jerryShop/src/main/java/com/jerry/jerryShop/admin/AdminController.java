package com.jerry.jerryShop.admin;
/*
 * JerryShop Product Controller
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.jerryShop.product.Product;
import com.jerry.jerryShop.product.ProductRepository;
import com.jerry.jerryShop.product.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

	private final ProductService productService;
	
	@GetMapping("/")
	public String index() {
		return "admin/adminContents";
	}

}
