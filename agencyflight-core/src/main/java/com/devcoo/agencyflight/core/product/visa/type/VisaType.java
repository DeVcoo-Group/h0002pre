package com.devcoo.agencyflight.core.product.visa.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "visa_types")
public class VisaType extends StdEntity {

	private static final long serialVersionUID = 394086897920131293L;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDisplayName() {
		if(getDescription() != null) {
			return getDescription();
		}
		return "";
	}

}
