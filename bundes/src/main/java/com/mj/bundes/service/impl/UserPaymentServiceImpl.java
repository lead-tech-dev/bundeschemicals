package com.mj.bundes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.bundes.domain.UserPayment;
import com.mj.bundes.repository.UserPaymentRepository;
import com.mj.bundes.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	@Autowired
	private UserPaymentRepository userPaymentRepository;
		
	public UserPayment findById(Long id) {
		return userPaymentRepository.findOne(id);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.delete(id);
	}
} 
