package com.jerry.jerryShop.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/*
 * JerryShop Member Info Form for modifying or confirming my info.
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-09
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@Getter
@Setter
public class MemberInfoForm {

	private String username;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String password1;
	
	@NotEmpty(message = "비밀번호를 다시 한 번 입력해주세요.")
	private String password2;
	
    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String email;
    
    private String mobile;
    
    private String address;
    
    private String postNumber;
    
    private String detailAddress;
    
    private String extraAddress;
    
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
}
