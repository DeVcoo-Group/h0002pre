package com.devcoo.agencyflight.core.document;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.std.StdEntity;

@Entity
@Table(name = "documents")
public class Document extends StdEntity {
	
	private static final long serialVersionUID = 1835208134687320983L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "document_type")
	private int documentType;

}
