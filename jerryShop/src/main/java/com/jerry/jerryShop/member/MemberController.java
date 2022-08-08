package com.jerry.jerryShop.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerry.jerryShop.util.EmailService;
import com.jerry.jerryShop.util.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * JerryShop Member Join Form
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final EmailService emailService;
	
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
		
		try {
			memberService.create(memberJoinForm.getUsername(), memberJoinForm.getPassword1(), memberJoinForm.getName(), memberJoinForm.getEmail(), memberJoinForm.getMobile(), 
					memberJoinForm.getAddress(), memberJoinForm.getPostNumber(), memberJoinForm.getDetailAddress(), memberJoinForm.getExtraAddress());
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("joinFailed", "이미 등록된 아이디 혹은 이메일 주소 입니다.");
			return "member/join_form";
		} catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("joinFailed", e.getMessage());
			return "member/join_form";
		}
		
		return "redirect:/";
	}
	
	/*
	 * login_form.html 템플릿을 렌더링하는 GET 방식의 login 메서드를 추가했다. 
	 * 실제 로그인을 진행하는 @PostMapping 방식의 메서드는 스프링 시큐리티가 대신 처리하므로 직접 구현할 필요가 없다.
	 */
	@GetMapping("/login")
	public String login() {
		return "member/login_form";
	}
	
	/*
	 * login_form.html 템플릿을 렌더링하는 GET 방식의 login 메서드를 추가했다. 
	 * 실제 로그인을 진행하는 @PostMapping 방식의 메서드는 스프링 시큐리티가 대신 처리하므로 직접 구현할 필요가 없다.
	 */
	@GetMapping("/findidpw")
	public String findidpw() {
		return "member/find_id_pw";
	}
	
	@PostMapping("/findidpw")
	public @ResponseBody Map<String, String> findidpw(String email, String name) {
		// 에러가 발생하면 회원가입 폼으로 돌아감
		// return to the join form if form has an errors
		Map<String, String> json = new HashMap<>();
		Optional<Member> _member = this.memberService.findUsernameByNameAndEmail(name, email);


        if (_member.isEmpty()) {
        	json.put("check", "false");
	       
        }else {
        	Member member = _member.get();
        	json.put("check", member.getUsername());
        }
		
        return json;
	}
	
	@GetMapping("/findPw")
	public @ResponseBody Map<String, Boolean> findPassword(String email, String username){
		Map<String, Boolean> json = new HashMap<>();
		Optional<Member> _member = this.memberService.findByUsernameAndEmail(email, username);
		if(_member.isEmpty()) {
			json.put("check", false);
		}else {
			json.put("check", true);
		}
		log.info(json.toString());
		return json;
	}
	
	@PostMapping("/findPw")
	public @ResponseBody void sendEmail(String email, String username) {
		log.info("findPW Controller 입장");
		Mail mail = this.emailService.createMailAndChangePassword(email, username);
		this.emailService.sendMail(mail);
		// return "member/find_id_pw";
	}
}
