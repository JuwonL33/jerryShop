package com.jerry.jerryShop.product;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String list(Model model, @AuthenticationPrincipal User user) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			List<Product> productList = this.productService.findAll();
			model.addAttribute("productList", productList);
			return "admin/product_list";
		}else {
			return "redirect:/member/login";
		}
	}

	
	@GetMapping("/create")
	public String create(@AuthenticationPrincipal User user) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			return "admin/product_create_form";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@PostMapping("/create")
	public String create(@RequestBody HashMap<String, Object> productFrm, @AuthenticationPrincipal User user) throws ParseException {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			log.info("...........productFrm : " + productFrm);
			this.productService.create(productFrm);
			return "redirect:/product/list";
		}else {
			return "redirect:/member/login";
		}
	}

	@GetMapping("/detail/{id}")
	public String detail(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			Product product = this.productService.detail(id);
			model.addAttribute("product", product);
			return "admin/product_detail";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			Product product = this.productService.detail(id);
			model.addAttribute("product", product);
			return "admin/product_modify";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@PostMapping("/modify/{id}")
	public String modify(@RequestBody HashMap<String, Object> productModifyFrm, @AuthenticationPrincipal User user, @PathVariable("id") Long id) throws ParseException {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			log.info("............productModifyFrm:"+productModifyFrm);
			this.productService.modify(id, productModifyFrm);
			return "admin/product_detail";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
		if(user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			System.out.println("들어왔다");
			Product product = this.productService.detail(id);
			this.productService.delete(product);
			return "admin/product_list";
		}else {
			return "redirect:/member/login";
		}
	}
}
