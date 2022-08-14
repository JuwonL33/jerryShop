package com.jerry.jerryShop;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jerry.jerryShop.admin.AdminService;
import com.jerry.jerryShop.category.Category;
import com.jerry.jerryShop.category.CategoryRepository;
import com.jerry.jerryShop.category.CategoryService;


@SpringBootTest
class JerryShopApplicationTests {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void contextLoads() {
		
		/*String productName = "오가닉체리향캔디55g";
		long price = 2200;
		int discountRatio = 0;
		long stock = 100;
		String image1 = "";
		String image2 = ""; 
		String image3 = ""; 
		String detail = "이탈리아산 유기농 과일잼이 함유된 오가닝체리향캔디"; 
		String delivery = "총 결제금액이 30,000원 미만시 배송비 3,000원이 청구됩니다.";
		String category1 = "candy";
		String category2 = "candy";*/
		
		Optional<Category> _cate = this.categoryRepository.findById(Long.parseLong("1"));
		Category parent = _cate.get();
		Category category = new Category(Long.parseLong("7"), "과일초콜릿", parent, Long.parseLong("2"), null);
		this.categoryRepository.save(category);
		
		
		
		
		// this.adminService.create(productName, price, discountRatio, stock, image1, image2, image3, detail, delivery, category1, category2);
	}

}
