package com.jerry.jerryShop.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * JerryShop Member Repository
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-07
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByusername(String username);
	
	Optional<Member> findUsernameByNameAndEmail(String name, String email);
	
	Optional<Member> findByUsernameAndEmail(String username, String email);
}
