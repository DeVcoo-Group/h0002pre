package com.devcoo.agencyflight.core.payment;

import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdDao;

public interface PaymentDao extends StdDao<Payment> {
	
	List<Payment> findByInvoice(Invoice invoice);

}
