package com.jerry.jerryShop.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/*
 * JerryShop Member Find Form for finding ID or PW
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
    
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
}
