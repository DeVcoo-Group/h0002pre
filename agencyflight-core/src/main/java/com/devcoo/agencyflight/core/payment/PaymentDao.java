package com.devcoo.agencyflight.core.payment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdDao;

public interface PaymentDao extends StdDao<Payment> {
	
	List<Payment> findByInvoice(Invoice invoice);
	@Query("select sum(p.amount) from Payment p where p.invoice = ?1")
	Double getTotalAmount(Invoice invoice);
}
