package com.mj.bundes.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.bundes.repository.CategoryRepository;
import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;
import com.mj.bundes.repository.ProductRepository;
import com.mj.bundes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	public List<Product> findAll() {
		List<Product> productList = (List<Product>) productRepository.findAll();
		List<Product> activeProductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isActive()) {
				activeProductList.add(product);
			}
		}

		return activeProductList;
	}
	
	
	public List<Product> sort(List<Product> products, String sortType){
		// 0: Price ASC, 1: Price DESC
		if(sortType.equals("0")){
			Collections.sort(products, Product.Comparators.PRICE);
		}
		if(sortType.equals("1")){
			Collections.sort(products, Product.Comparators.PRICE);
			Collections.reverse(products);
		}
		return products;
	}
	

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}

	public void getVisited(Long productId){
		Product product = productRepository.findOne(productId);
		product.setProductViews(product.getProductViews()+1);
		productRepository.save(product);
	}
	
	public List<Product> getProductsByMainCategory(String mainCategoryName){
		List<Category> categoryList = categoryRepository.findAllByMainCategoryName(mainCategoryName); 
		List<Product> products = new ArrayList<Product>();
		for(Category category: categoryList){
			for(Product product : category.getProducts()){
				products.add(product);
			}
		}
		return products;
	}
	
	public List<Product> getProductsByCategory(Category category){
		return productRepository.findAllByProductCategory(category);
	}

	public List<Product> blurrySearch(String title) {
		List<Product> productList = productRepository.findByNameContaining(title);
		List<Product> activeProductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isActive()) {
				activeProductList.add(product);
			}
		}

		return activeProductList;
	}

	public void removeOne(Long id){
		productRepository.delete(id);
	}
}
