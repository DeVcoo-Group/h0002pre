package com.devcoo.agencyflight.core.product.visa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "visas")
public class Visa extends StdEntity {

	private static final long serialVersionUID = -581850811903543205L;
	
	@ManyToOne
	@JoinColumn(name = "period")
	private Period period;
	
	@ManyToOne
	@JoinColumn(name = "visa_type", nullable = false)
	private VisaType visaType;
	
	@ManyToOne
	@JoinColumn(name = "nationality")
	private Country nationality;
	
	@Column(name = "find_fee")
	private Double findFee;

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public VisaType getVisaType() {
		return visaType;
	}

	public void setVisaType(VisaType visaType) {
		this.visaType = visaType;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public Double getFindFee() {
		return findFee;
	}

	public void setFindFee(Double findFee) {
		this.findFee = findFee;
	}
	
	@Override
	public String getDisplayName() {
		return "";
	}

}
