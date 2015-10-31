package com.devcoo.agencyflight.core.payment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.user.User;

@Entity
@Table(name="payment")
public class Payment extends StdEntity {
	private static final long serialVersionUID = -8142565987479356966L;

	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "invoice")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private User employee;
	
	
	public User getEmployee() {
		return employee;
	}


	public void setEmployee(User employee) {
		this.employee = employee;
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


	@Override
	public String getDisplayName() {
		if(getAmount() != null) {
			return getAmount().toString();
		}
		return "";
	}

}
