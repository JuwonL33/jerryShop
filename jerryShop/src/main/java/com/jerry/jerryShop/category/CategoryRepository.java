package com.jerry.jerryShop.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * JerryShop Category Repository
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-14
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value="select c from Category c where c.parent is NULL")
	List<Category> findAll();
	
	List<Category> findByParent(Category category);
}
