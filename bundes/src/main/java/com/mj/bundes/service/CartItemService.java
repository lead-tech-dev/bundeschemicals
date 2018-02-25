package com.mj.bundes.service;

import java.util.List;

import com.mj.bundes.domain.Product;
import com.mj.bundes.domain.CartItem;
import com.mj.bundes.domain.Order;
import com.mj.bundes.domain.ShoppingCart;
import com.mj.bundes.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addProductToCartItem(Product book, User user, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
