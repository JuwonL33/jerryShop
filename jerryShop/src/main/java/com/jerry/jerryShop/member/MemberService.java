package com.jerry.jerryShop.member;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;
	
	public Member create(String userId, String password, String name, String email, 
			String mobile, String address, String postNumber, String detailAddress, String extraAddress) {
		Member member = new Member();
		member.setUsername(userId);
		member.setPassword(passwordEncoder.encode(password));
		member.setName(name);
		member.setEmail(email);
		member.setMobile(mobile);
		member.setAddress(address);
		member.setPostNumber(postNumber);
		member.setDetailAddress(detailAddress);
		member.setExtraAddress(extraAddress);
		member.setJoinedDate(LocalDateTime.now());
		member.setMemberGrade("blue");
		this.memberRepository.save(member);
		return member;
	}
	
	public Optional<Member> findUsernameByNameAndEmail(String name, String email) {
        return this.memberRepository.findUsernameByNameAndEmail(name, email);
	}
	
	public Optional<Member> findByUsernameAndEmail(String email, String username) {
		return this.memberRepository.findByUsernameAndEmail(username, email);
	}
	
	public Optional<Member> findByusername(String username) {
		return this.memberRepository.findByusername(username);
	}
	
	public Member updateMyInfo(Member member, String name, String email, 
			String mobile, String address, String postNumber, String detailAddress, String extraAddress) {
		member.setName(name);
		member.setEmail(email);
		member.setMobile(mobile);
		member.setAddress(address);
		member.setPostNumber(postNumber);
		member.setDetailAddress(detailAddress);
		member.setExtraAddress(extraAddress);
		this.memberRepository.save(member);
		return member;
	}
}
