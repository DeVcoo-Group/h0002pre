package com.devcoo.agencyflight.core.product.visa.period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "periods")
public class Period extends StdEntity {

	private static final long serialVersionUID = 293618679248113621L;
	
	@Column(name = "day", nullable = false)
	private String day;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String getDisplayName() {
		if(getDay() != null) {
			return getDay();
		}
		return "";
	}
	
}
