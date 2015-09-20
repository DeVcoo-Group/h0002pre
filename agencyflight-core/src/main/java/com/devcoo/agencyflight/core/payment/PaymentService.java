package com.devcoo.agencyflight.core.payment;

import com.devcoo.agencyflight.core.std.StdService;

public interface PaymentService extends StdService<Payment> {

	Payment saveAndFlush(Payment entity);
}
