package com.mj.bundes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
	
	List<Product> findAllByOrderByProductViewsDesc();

	List<Product> findAllByProductCategory(Category category);

	List<Product> findByNameContaining(String name);
	

}
