package com.devcoo.agencyflight.core.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.std.StdServiceImp;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.util.CodeGenerator;

@Service
@Transactional
public class InvoiceServiceImp extends StdServiceImp<InvoiceDao, Invoice> implements InvoiceService {

	@Autowired
	private InvoiceDao dao;

	@Override
	public Invoice createInvoice(Invoice entity) {
		entity = save(entity);
		entity.setCode(CodeGenerator.getGenerateCode(CodeGenerator.TYPE_INVOICE, entity));
		return save(entity);
	}

	@Override
	public Invoice newInvoice(Integer cusId) {
		Invoice invoice = new Invoice();
		CustomerService customerService = (CustomerService) ApplicationContext.getContext().getBean("customerServiceImp");
		invoice.setCustomer(customerService.find(cusId));
		User employee = ApplicationContext.getLog_user();
		invoice.setEmployee(employee);
		invoice.setStatus(InvoiceStatus.NEW);
		return invoice;
	}

}
