package com.devcoo.agencyflight.core.invoice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class InvoiceVisaServiceImp extends StdServiceImp<InvoiceVisaDao, InvoiceVisa> implements InvoiceVisaService {

}
