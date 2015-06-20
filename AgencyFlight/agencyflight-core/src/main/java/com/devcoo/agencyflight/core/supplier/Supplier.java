package com.devcoo.agencyflight.core.supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name="supplier")
public class Supplier extends StdEntity {
	
	private static final long serialVersionUID = 8700699310917294214L;

	@Column(name = "name", nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
