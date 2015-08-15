package com.devcoo.agencyflight.core.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class InvoiceServiceImp extends StdServiceImp<InvoiceDao, Invoice> implements InvoiceService {

	@Autowired
	private InvoiceDao dao;

	@Override
	public Invoice saveAndFlush(Invoice entity) {
		return dao.saveAndFlush(entity);
	}

}
