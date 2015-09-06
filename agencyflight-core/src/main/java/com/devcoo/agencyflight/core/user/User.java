package com.devcoo.agencyflight.core.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "users")
public class User extends StdEntity implements Serializable {
	
	private static final long serialVersionUID = 8397670116185326543L;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private Integer role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String getDisplayName() {
		if(getName() != null) {
			return getName();
		}
		return "";
	}
	
}
