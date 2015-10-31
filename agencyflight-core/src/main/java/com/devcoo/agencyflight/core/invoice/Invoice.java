package com.devcoo.agencyflight.core.invoice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.invoice.article.InvoiceArticle;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.util.Tools;

@Entity
@Table(name = "invoices")
public class Invoice extends StdEntity {

	private static final long serialVersionUID = -1503906552312944843L;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", nullable = false)
	private User employee;
	
	@Column(name = "deposit")
	private Double deposit;
	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<InvoiceArticle> articles;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public List<InvoiceArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<InvoiceArticle> articles) {
		this.articles = articles;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public InvoiceStatus getEStatus() {
		return (InvoiceStatus) Tools.getEnum(getStatus(), InvoiceStatus.values());
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setStatus(InvoiceStatus status) {
		if (status != null) {
			setStatus(status.getId());
		} else {
			this.status = null;
		}
	}

	@Override
	public String getDisplayName() {
		if(getCode() != null) {
			return getCode();
		}
		return "";
	}
	
	public List<InvoiceArticle> getArticlesNotDelete() {
		List<InvoiceArticle> articles = new ArrayList<InvoiceArticle>();
		if (getArticles() != null) {
			for (InvoiceArticle article : getArticles()) {
				if (!article.isDelete()) {
					articles.add(article);
				}
			}
		}
		return articles;
	}
	
	public Double getTotalAmountToPaid() {
		Double amount = 0d;
		for (InvoiceArticle article : getArticlesNotDelete()) {
			amount += article.getTotal();
		}
		return amount;
	}

}
