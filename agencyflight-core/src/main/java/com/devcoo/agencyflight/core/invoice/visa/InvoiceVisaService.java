package com.devcoo.agencyflight.core.invoice.visa;

import com.devcoo.agencyflight.core.std.StdService;

public interface InvoiceVisaService extends StdService<InvoiceVisa> {

	InvoiceVisa saveAndFlush(InvoiceVisa entity);
}
