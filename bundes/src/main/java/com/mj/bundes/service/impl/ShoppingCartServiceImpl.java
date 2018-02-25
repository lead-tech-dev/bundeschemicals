package com.mj.bundes.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.bundes.domain.CartItem;
import com.mj.bundes.domain.ShoppingCart;
import com.mj.bundes.repository.ShoppingCartRepository;
import com.mj.bundes.service.CartItemService;
import com.mj.bundes.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getProduct().getInStockNumber() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		
		shoppingCart.setGrandTotal(cartTotal);
		
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
	}
	
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		
		shoppingCart.setGrandTotal(new BigDecimal(0));
		
		shoppingCartRepository.save(shoppingCart);
	}

}
