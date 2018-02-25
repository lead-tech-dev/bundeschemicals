package com.mj.bundes.service;

import com.mj.bundes.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	
	void removeById(Long id);
}
