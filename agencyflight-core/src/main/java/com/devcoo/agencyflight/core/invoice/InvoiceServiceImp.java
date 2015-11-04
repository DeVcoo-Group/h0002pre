package com.devcoo.agencyflight.core.invoice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.std.StdServiceImp;
import com.devcoo.agencyflight.core.user.User;

@Service
@Transactional
public class InvoiceServiceImp extends StdServiceImp<InvoiceDao, Invoice> implements InvoiceService {

	@Autowired
	private InvoiceDao dao;

	@Override
	public Invoice saveAndFlush(Invoice entity) {
		return dao.saveAndFlush(entity);
	}

	@Override
	public Invoice createInvoice(Invoice entity) {
		entity.setCode(new Date() + "");
		User employee = ApplicationContext.getLog_user();
		entity.setEmployee(employee);
		entity.setStatus(InvoiceStatus.NEW);
		return save(entity);
	}

}
