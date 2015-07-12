package com.devcoo.agencyflight.core.invoice.visa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class InvoiceVisaServiceImp extends StdServiceImp<InvoiceVisaDao, InvoiceVisa> implements InvoiceVisaService {

	@Autowired
	private InvoiceVisaDao dao;

	@Override
	public InvoiceVisa saveAndFlush(InvoiceVisa entity) {
		return dao.saveAndFlush(entity);
	}

}
