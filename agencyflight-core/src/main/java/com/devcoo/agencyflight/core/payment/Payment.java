package com.devcoo.agencyflight.core.payment;

import javax.persistence.Column;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdEntity;
/**
 * 
 * @author midmike
 *
 */
public class Payment extends StdEntity {

	private static final long serialVersionUID = 2672831654834586916L;
	@Column(name = "amount", nullable = false, unique = true)
	private Double amount;
	@Column(name = "invoice", nullable = false, unique = true)
	private Invoice invoice;

	@Override
	public String getDisplayName() {
		return amount.toString();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	

}
