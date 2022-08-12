package com.jerry.jerryShop.product;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/*
 * JerryShop Product Repository
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

/*
 * delivery : "1" -> 일반배송
 * delivery : "2" -> 무료배송
 */

@Getter
@Setter
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String productName;
	
	private long price;
	
	private long discount;
	
	private int discountRatio;
	
	private long stock;
	
	private String imageName;
	
	private String imagePath;
	
	private String detail;
	
	private String delivery;
	
	private String category1;
	
	private String category2;
	
	private String origin;
	
	private LocalDateTime registrationDate;
	
	private int hit;
	
	private int saleCount;
}
