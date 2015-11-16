package com.devcoo.agencyflight.core.payment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.invoice.InvoiceStatus;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class PaymentServiceImp extends StdServiceImp<PaymentDao, Payment> implements PaymentService {

	@Override
	public void paid(Invoice invoice) {
		InvoiceService invoiceService = (InvoiceService) ApplicationContext.getContext().getBean("invoiceServiceImp");
		invoice.setStatus(InvoiceStatus.FULL_PAY);
		invoiceService.save(invoice);
		paid(invoice, invoice.getTotalAmountToPaid());
	}

	@Override
	public void paid(Invoice invoice, Double amount) {
		Payment payment = new Payment();
		payment.setInvoice(invoice);
		payment.setAmount(amount);
		payment.setEmployee(ApplicationContext.getLog_user());
		save(payment);
	}

	@Override
	public List<Payment> findByInvoice(Invoice invoice) {
		return dao.findByInvoice(invoice);
	}

	@Override
	public Double getTotalAmount(Invoice invoice) {
		return dao.getTotalAmount(invoice);
	}

}
