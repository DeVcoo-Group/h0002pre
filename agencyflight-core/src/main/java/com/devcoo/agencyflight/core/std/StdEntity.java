package com.devcoo.agencyflight.core.std;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.devcoo.agencyflight.core.user.User;

@MappedSuperclass
public class StdEntity implements Serializable {
	
	private static final long serialVersionUID = -3327595028866832476L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name="active")
	private Boolean active;
	@Column(name="createDate")
	private Date createDate;
	@Column(name="modifyDate")
	private Date modifyDate;
	@Column(name="lastModifier")
	private Integer lastModifier;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(Integer lastModifier) {
		this.lastModifier = lastModifier;
	}
	
	
}
