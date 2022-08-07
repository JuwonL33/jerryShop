package com.jerry.jerryShop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * JerryShop Home Controller
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home() {
		return "layout";
	}
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/home";
	}
	
}
