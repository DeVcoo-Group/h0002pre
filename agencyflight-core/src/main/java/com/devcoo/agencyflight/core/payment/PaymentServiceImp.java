package com.devcoo.agencyflight.core.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class PaymentServiceImp extends StdServiceImp<PaymentDao, Payment> implements PaymentService {

	@Autowired
	private PaymentDao dao;

	@Override
	public Payment saveAndFlush(Payment entity) {
		return dao.saveAndFlush(entity);
	}

}
