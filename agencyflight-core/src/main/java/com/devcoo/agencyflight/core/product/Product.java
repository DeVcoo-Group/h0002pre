package com.devcoo.agencyflight.core.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.supplier.Supplier;

@Entity
@Table(name="products")
public class Product extends StdEntity {

	/** */
	private static final long serialVersionUID = 2353681075201545704L;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "product_type", nullable = false)
	private Integer productType;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "visa_id")
	private Visa visa;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Visa getVisa() {
		return visa;
	}

	public void setVisa(Visa visa) {
		this.visa = visa;
	}

	@Override
	public String getDisplayName() {
		if(getName() != null) {
			return getName();
		}
		return "";
	}

}
