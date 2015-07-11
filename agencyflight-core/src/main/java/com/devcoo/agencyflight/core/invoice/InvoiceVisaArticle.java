package com.devcoo.agencyflight.core.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "invoiceVisaArticles")
public class InvoiceVisaArticle extends StdEntity {

	private static final long serialVersionUID = -1219281060518919149L;
	
//	@Column(name = "code", nullable = false, unique = true)
//	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invoice_id", nullable = false)
	private InvoiceVisa invoice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "visa_id", nullable = false)
	private Visa visa;

//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public InvoiceVisa getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceVisa invoice) {
		this.invoice = invoice;
	}

	public Visa getVisa() {
		return visa;
	}

	public void setVisa(Visa visa) {
		this.visa = visa;
	}

}
