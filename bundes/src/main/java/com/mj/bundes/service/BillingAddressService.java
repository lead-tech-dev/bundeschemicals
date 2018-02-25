package com.mj.bundes.service;

import com.mj.bundes.domain.BillingAddress;
import com.mj.bundes.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
