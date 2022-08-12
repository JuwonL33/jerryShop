package com.jerry.jerryShop.product;

import java.security.Principal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * JerryShop Product Controller
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	
	@GetMapping("/list")
	public String product_list(Model model, @AuthenticationPrincipal User user) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			List<Product> productList = this.productService.findAll();
			model.addAttribute("productList", productList);
			return "admin/product_list";
		}else {
			return "redirect:/member/login";
		}
	}

	
	@GetMapping("/create")
	public String product_create(@AuthenticationPrincipal User user) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			return "admin/product_create_form";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@PostMapping("/create")
	public String product_create(@RequestBody HashMap<String, Object> productFrm, @AuthenticationPrincipal User user) throws ParseException {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			log.info("...........productFrm : " + productFrm);
			this.productService.create(productFrm);
			return "redirect:/product/list";
		}else {
			return "redirect:/member/login";
		}
	}
}
