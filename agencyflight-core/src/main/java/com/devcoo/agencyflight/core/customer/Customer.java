package com.devcoo.agencyflight.core.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name="customers")
public class Customer extends StdEntity {
	
	private static final long serialVersionUID = 8700699310917294214L;

	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "birth_place")
	private String birthPlace;
	
	@Column(name = "nationality")
	private Integer nationality;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

}
