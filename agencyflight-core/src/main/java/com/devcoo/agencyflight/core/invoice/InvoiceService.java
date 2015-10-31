package com.devcoo.agencyflight.core.invoice;

import com.devcoo.agencyflight.core.std.StdService;

public interface InvoiceService extends StdService<Invoice> {

	Invoice saveAndFlush(Invoice entity);
	
	Invoice createInvoice(Invoice entity);
}
