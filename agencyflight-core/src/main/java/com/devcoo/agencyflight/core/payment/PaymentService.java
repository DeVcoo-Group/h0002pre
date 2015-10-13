package com.devcoo.agencyflight.core.payment;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdService;

public interface PaymentService extends StdService<Payment> {
	
	void paid(Invoice invoice);
	void paid(Invoice invoice, Double amount);

}
