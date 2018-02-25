package com.mj.bundes.service;

import com.mj.bundes.domain.BillingAddress;
import com.mj.bundes.domain.Order;
import com.mj.bundes.domain.Payment;
import com.mj.bundes.domain.ShippingAddress;
import com.mj.bundes.domain.ShoppingCart;
import com.mj.bundes.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user);
	
	Order findOne(Long id);
}
