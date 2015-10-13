package com.devcoo.agencyflight.core.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class PaymentServiceImp extends StdServiceImp<PaymentDao, Payment> implements PaymentService {

	@Override
	public void paid(Invoice invoice) {
		paid(invoice, invoice.getTotalAmountToPaid());
	}

	@Override
	public void paid(Invoice invoice, Double amount) {
		Payment payment = new Payment();
		payment.setInvoice(invoice);
		payment.setAmount(amount);
		save(payment);
	}

}
