package com.jerry.jerryShop.admin;



import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jerry.jerryShop.product.Product;
import com.jerry.jerryShop.product.ProductRepository;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Admin Service
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@RequiredArgsConstructor
@Service
public class AdminService {

	private final ProductRepository productRepository;
	
	public Product create(String productName, long price, int discountRatio, long stock, 
			String image1, String image2, String image3, String detail, String delivery, 
			String category1, String category2) {
		
		Product product = new Product();
		
		product.setProductName(productName);
		product.setPrice(price);
		product.setDiscountRatio(discountRatio);
		product.setDiscount(price-(price*discountRatio/100));
		product.setStock(stock);
		product.setImage1(image1);
		product.setImage2(image2);
		product.setImage3(image3);
		product.setDetail(detail);
		product.setDelivery(delivery);
		product.setCategory1(category1);
		product.setCategory2(category2);
		product.setSaleCount(0);
		product.setHit(0);
		product.setRegistrationDate(LocalDateTime.now());
		
		this.productRepository.save(product);
		return product;
	}
}
