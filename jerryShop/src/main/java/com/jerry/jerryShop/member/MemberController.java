package com.jerry.jerryShop.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Member Join Form
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */


@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/join")
	public String join(MemberJoinForm memberJoinForm) {
		return "member/join_form";
	}
	
	@PostMapping("/join")
	public String join(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult) {
		
		// 에러가 발생하면 회원가입 폼으로 돌아감
		// return to the join form if form has an errors
		if (bindingResult.hasErrors()) {
			return "member/join_form";
		}
		
		// 비밀번호1과 비밀번호2가 일치하지 않으면 회원가입 폼으로 돌아감
		// return to the join form if password1 isn't matched to password2
		if (!memberJoinForm.getPassword1().equals(memberJoinForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
			return "member/join_form";
		}
		
		memberService.create(memberJoinForm.getUserId(), memberJoinForm.getPassword1(), memberJoinForm.getName(), memberJoinForm.getEmail(), memberJoinForm.getMobile(), memberJoinForm.getAddress(), memberJoinForm.getPostNumber());
		
		return "redirect:/";
	}
}
