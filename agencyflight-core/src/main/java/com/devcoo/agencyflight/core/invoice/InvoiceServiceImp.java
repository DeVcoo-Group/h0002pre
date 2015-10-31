package com.devcoo.agencyflight.core.invoice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.std.StdServiceImp;
import com.devcoo.agencyflight.core.user.User;
import com.vaadin.ui.UI;

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
		WebContext context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		User employee = context.getLog_user();
		entity.setEmployee(employee);
		entity.setStatus(InvoiceStatus.NEW);
		save(entity);
		return entity;
	}

}
