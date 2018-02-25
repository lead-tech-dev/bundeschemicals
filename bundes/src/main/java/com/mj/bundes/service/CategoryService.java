package com.mj.bundes.service;

import java.util.List;

import com.mj.bundes.domain.Category;

public interface CategoryService {
	
	List<Category> getAllCategory();
	
	List<String> getAllMainCategory();

	List<String> getAllSubCategory();
	
	void save(Category category);
	
	void delete(Long categoryId);
	
	Category getCategoryById(Long categoryId);
}
