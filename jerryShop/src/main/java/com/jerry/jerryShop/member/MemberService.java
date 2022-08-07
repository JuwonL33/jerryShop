package com.jerry.jerryShop.member;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Member Service
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public Member create(String userId, String password, String name, String email, 
			String mobile, String address, String postNumber) {
		Member member = new Member();
		member.setUserId(userId);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(password));
		member.setName(name);
		member.setEmail(email);
		member.setMobile(mobile);
		member.setAddress(address);
		member.setPostNumber(postNumber);
		member.setJoinedDate(LocalDateTime.now());
		member.setMemberGrade("blue");
		this.memberRepository.save(member);
		return member;
	}
}
