package com.mj.bundes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mj.bundes.domain.ProductToCartItem;
import com.mj.bundes.domain.CartItem;

@Transactional
public interface ProductToCartItemRepository extends CrudRepository<ProductToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
