package com.devcoo.agencyflight.core.invoice.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "invoice_articles")
public class InvoiceArticle extends StdEntity {

	private static final long serialVersionUID = -1219281060518919149L;
	
//	@Column(name = "code", nullable = false, unique = true)
//	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "unit", nullable = false)
	private Integer unit;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

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

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product Product) {
		this.product = Product;
	}

	@Override
	public String getDisplayName() {
		return getName();
	}

}
