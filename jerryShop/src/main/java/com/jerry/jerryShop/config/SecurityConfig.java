package com.jerry.jerryShop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jerry.jerryShop.member.MemberSecurityService;

import lombok.RequiredArgsConstructor;

/*
 * JerryShop Security Config
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final MemberSecurityService memberSecurityService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
        .and()
            .csrf().ignoringAntMatchers("/h2-console/**")
        .and()
            .headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        .and()
        	.formLogin()							// 스프링 시큐리티의 로그인 설정을 담당하는 부분
            .loginPage("/member/login")				// 로그인 페이지의 URL
        	.defaultSuccessUrl("/")					// 로그인 성공시 이동하는 디폴트 페이지는 루트
        .and()
        	.logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        	.logoutSuccessUrl("/")
        	.invalidateHttpSession(true)
        ;
    return http.build();     
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * AuthenticationManager의 빈 생성. 이는 스프링 시큐리티의 인증을 담당한다.
	 * AuthenticationManager의 빈 생성시 스프링의 내부 동작으로 인해 위에서 작성한 UserSecurityService와 PasswordEncoder가 자동으로 설정됨.
	 */
	 @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}