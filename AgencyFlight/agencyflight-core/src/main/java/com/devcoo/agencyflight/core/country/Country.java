package com.devcoo.agencyflight.core.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "countries")
public class Country extends StdEntity {

	private static final long serialVersionUID = -278159063230365160L;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code_2", nullable = false, unique = true, length = 2)
	private String code2;
	
	@Column(name = "code3", unique = true, length = 3)
	private String code3;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	@Override
	public String getDisplayName() {
		return getName();
	}

}
