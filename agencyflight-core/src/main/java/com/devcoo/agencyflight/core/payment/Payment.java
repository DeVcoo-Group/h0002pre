package com.devcoo.agencyflight.core.payment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name="payment")
public class Payment extends StdEntity {
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "invoice")
	private Invoice invoice;
	
	
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


	@Override
	public String getDisplayName() {
		if(getAmount() != null) {
			return getAmount().toString();
		}
		return "";
	}

}
