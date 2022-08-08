package com.jerry.jerryShop.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class MemberFindPwForm {
	/*
	 * JerryShop Member Find PW Form for finding PW
	 * Author : Jerry Juwon Lee
	 * Version : 1.0
	 * Date : 2022-08-08
	 * Copyright (C) 2022 by Jerry, All right reserved.
	 */

	@Getter
	@Setter
	public class MemberFindForm {
	    @NotEmpty(message = "이메일을 입력해주세요.")
	    @Email
	    private String email;
	    
	    @NotEmpty(message = "아이디를 입력해주세요.")
	    private String username;
	}

}
