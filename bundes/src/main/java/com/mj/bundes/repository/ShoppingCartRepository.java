package com.mj.bundes.repository;

import org.springframework.data.repository.CrudRepository;

import com.mj.bundes.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
