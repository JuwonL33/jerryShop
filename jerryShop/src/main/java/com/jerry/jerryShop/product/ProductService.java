package com.jerry.jerryShop.product;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jerry.jerryShop.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Product Service
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@RequiredArgsConstructor
@Service
public class ProductService {
private final ProductRepository productRepository;
	
	public List<Product> findAll(){
		return this.productRepository.findAll();
	}

	public void create(HashMap<String, Object> productFrm) throws ParseException {
		String productName = productFrm.get("productName").toString();
		long price = Long.parseLong(productFrm.get("price").toString());
		int discountRatio = Integer.parseInt(productFrm.get("discountRatio").toString());
		long stock = Long.parseLong(productFrm.get("stock").toString());
		
		String origin = productFrm.get("origin").toString();
		String imageName = productFrm.get("imageName").toString();
		String imagePath = productFrm.get("imagePath").toString();
		String detail = productFrm.get("detail").toString();
		String delivery = productFrm.get("delivery").toString();

		String category1 = productFrm.get("category1").toString();
		String category2 = productFrm.get("category2").toString();
		
		Product product = new Product();

		product.setProductName(productName);
		product.setPrice(price);
		product.setDiscountRatio(discountRatio);
		product.setDiscount(price-(price*discountRatio/100));
		product.setStock(stock);
		product.setOrigin(origin);
		product.setImageName(imageName);
		product.setImagePath(imagePath);
		product.setDetail(detail);
		product.setDelivery(delivery);
		product.setCategory1(category1);
		product.setCategory2(category2);
		product.setSaleCount(0);
		product.setHit(0);
		product.setRegistrationDate(LocalDateTime.now());
		
		this.productRepository.save(product);

	}
	
	public Product detail(Long id) {
		Optional<Product> product = this.productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		} else {
			throw new DataNotFoundException("this product is not exist");
		}
	}
	
	public void modify(Long id, HashMap<String, Object> productFrm) throws ParseException {
		
		String productName = productFrm.get("productName").toString();
		long price = Long.parseLong(productFrm.get("price").toString());
		int discountRatio = Integer.parseInt(productFrm.get("discountRatio").toString());
		long stock = Long.parseLong(productFrm.get("stock").toString());
		
		String origin = productFrm.get("origin").toString();
		String imageName = productFrm.get("imageName").toString();
		String imagePath = productFrm.get("imagePath").toString();
		String detail = productFrm.get("detail").toString();
		String delivery = productFrm.get("delivery").toString();

		String category1 = productFrm.get("category1").toString();
		String category2 = productFrm.get("category2").toString();
		
		Optional<Product> _product = this.productRepository.findById(id);
		if(_product.isEmpty()) {
			throw new DataNotFoundException("this product is not exist");
		} 
		
		Product product = _product.get();

		product.setProductName(productName);
		product.setPrice(price);
		product.setDiscountRatio(discountRatio);
		product.setDiscount(price-(price*discountRatio/100));
		product.setStock(stock);
		product.setOrigin(origin);
		product.setImageName(imageName);
		product.setImagePath(imagePath);
		product.setDetail(detail);
		product.setDelivery(delivery);
		product.setCategory1(category1);
		product.setCategory2(category2);
		product.setSaleCount(0);
		product.setHit(0);
		product.setRegistrationDate(LocalDateTime.now());
		
		this.productRepository.save(product);

	}
	
	public void delete(Product product) {
		this.productRepository.delete(product);
	}
}
