package com.devcoo.agencyflight.fe.ui.printer.invoice;

import com.devcoo.agencyflight.core.ui.layout.report.JRBean;

public class SubInvoiceJRBean extends JRBean {
	private String name;
	private String id;
	private String qty;
	private String price;
	private String total;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	

}
