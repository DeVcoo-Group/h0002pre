package com.devcoo.agencyflight.core.product.visa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name="visa")
public class Visa extends StdEntity {

	/** */
	private static final long serialVersionUID = 2353681075201545704L;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "service_charge")
	private Double serviceCharge;
	
	@Column(name = "product_type")
	private Integer productType;
	
	@Column(name = "requirement")
	private String requirement;//What documents that customer need to provide to us
	
	@Column(name = "passportVisaPeriod")
	private Integer passportVisaPeriod;//How many day for visa or passport delay or create

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

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Integer getPassportVisaPeriod() {
		return passportVisaPeriod;
	}

	public void setPassportVisaPeriod(Integer passportVisaPeriod) {
		this.passportVisaPeriod = passportVisaPeriod;
	}

}
