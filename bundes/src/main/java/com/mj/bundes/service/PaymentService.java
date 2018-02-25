package com.mj.bundes.service;

import com.mj.bundes.domain.Payment;
import com.mj.bundes.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
