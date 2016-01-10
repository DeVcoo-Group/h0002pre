package com.devcoo.agencyflight.core.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name="store")
public class Store extends StdEntity {
	private static final long serialVersionUID = -8142565987479356966L;
	
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String logoPath;
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLogoPath() {
		return logoPath;
	}



	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}



	@Override
	public String getDisplayName() {
		if(getName() != null) {
			return getName().toString();
		}
		return "";
	}

}
