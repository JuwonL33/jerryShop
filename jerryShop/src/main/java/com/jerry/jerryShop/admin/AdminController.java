package com.jerry.jerryShop.admin;
/*
 * JerryShop Product Controller
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerry.jerryShop.product.Product;
import com.jerry.jerryShop.product.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

	private final ProductRepository productRepository;
	
	@GetMapping("/index")
	public String index() {
		return "admin/admin_page";
	}
	
	@GetMapping("/product/list")
	public String product_list(Model model) {
		
		List<Product> productList = this.productRepository.findAll();
		
		model.addAttribute("productList", productList);
		return "admin/product_list";
	}
	
	@GetMapping("/product/create")
	public String product_create() {
		log.info("들어왔다!");
		return "admin/product_create_form";
	}
}
