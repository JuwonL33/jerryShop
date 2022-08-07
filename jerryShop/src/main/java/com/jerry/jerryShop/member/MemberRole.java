package com.jerry.jerryShop.member;

import lombok.Getter;

/*
 * JerryShop Member Role for granting to member after authentication
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */


@Getter
public enum MemberRole {
	ADMIN("ROLE_ADMIN"),							// 관리자
	USER("ROLE_USER"),								// 일반 회원
	SOCIAL_USER("ROLE_SOCIAL_USER");				// 소셜로그인으로 가입한 회원
	
	MemberRole(String value){
		this.value = value;
	}
	
	private String value;
}
