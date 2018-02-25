package com.mj.bundes.service;

import com.mj.bundes.domain.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void removeById(Long id);
}
