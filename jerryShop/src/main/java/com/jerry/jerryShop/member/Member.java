package com.jerry.jerryShop.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/*
 * JerryShop Member Entity
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@Getter
@Setter
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String mobile;
	
	private LocalDateTime joinedDate;
	
	private String address;
	
	private String postNumber;
	
	private String detailAddress;
	
	private String extraAddress;
	
	private String name;
	
	@Column(columnDefinition = "varchar(10) default 'blue'")
	private String memberGrade;
	
	@Column(columnDefinition = "char(1) default 'N'")
	private char deleteYn;
	
}
