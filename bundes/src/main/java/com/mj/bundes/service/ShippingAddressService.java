package com.mj.bundes.service;

import com.mj.bundes.domain.ShippingAddress;
import com.mj.bundes.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
