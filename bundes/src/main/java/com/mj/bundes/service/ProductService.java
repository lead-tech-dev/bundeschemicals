package com.mj.bundes.service;

import java.util.List;

import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;

public interface ProductService {
	Product save(Product Product);

	List<Product> findAll();
	
	Product findOne(Long id);
	

	
	void getVisited(Long productId);
	
	List<Product> getProductsByMainCategory(String mainCategoryName);
	
	List<Product> getProductsByCategory(Category category);
	
	List<Product> blurrySearch(String title);
	void removeOne(Long id);
	List<Product> sort(List<Product> products, String sortType);
}
