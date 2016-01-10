package com.devcoo.agencyflight.fe.ui.printer.invoice;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.ui.layout.report.JRBean;

public class InvoiceJRBean extends JRBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6678019284689723770L;
	private Customer customer;
	private List<SubInvoiceJRBean> listInvoiceArticle = new ArrayList<SubInvoiceJRBean>();
	private Double totalAmount;
	private Double totalPaid;
	private Double totalRemain;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<SubInvoiceJRBean> getListInvoiceArticle() {
		return listInvoiceArticle;
	}

	public void setListInvoiceArticle(List<SubInvoiceJRBean> listInvoiceArticle) {
		this.listInvoiceArticle = listInvoiceArticle;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Double getTotalRemain() {
		return totalRemain;
	}

	public void setTotalRemain(Double totalRemain) {
		this.totalRemain = totalRemain;
	}
	
}
