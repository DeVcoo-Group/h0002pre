package com.devcoo.agencyflight.fe.ui.printer.invoice;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.article.InvoiceArticle;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.store.Store;
import com.devcoo.agencyflight.core.ui.layout.report.ReportGenerator;

public class InvoicePrinterJasper extends ReportGenerator<InvoiceJRBean> {
	Invoice invoice = new Invoice();
	@Override
	public List<InvoiceJRBean> getData() {
		List<InvoiceJRBean> list = new ArrayList<InvoiceJRBean>();
		Store store = ApplicationContext.getStore();
		InvoiceJRBean invoiceJRBean = new InvoiceJRBean();
		invoiceJRBean.setStore(store);
		///////////////////////////////
		List<SubInvoiceJRBean> listSubInvoice = new ArrayList<SubInvoiceJRBean>();
		for(InvoiceArticle invoiceArticle : invoice.getArticlesNotDelete()) {
			SubInvoiceJRBean subInvoiceJRBean = new SubInvoiceJRBean();
			subInvoiceJRBean.setId(invoiceArticle.getId()+"");
			subInvoiceJRBean.setName(invoiceArticle.getName());
			subInvoiceJRBean.setQty(invoiceArticle.getUnit()+"");
			subInvoiceJRBean.setPrice(invoiceArticle.getPrice()+"");
			subInvoiceJRBean.setTotal((invoiceArticle.getPrice()*invoiceArticle.getUnit())+"");
			listSubInvoice.add(subInvoiceJRBean);
		}
		invoiceJRBean.setCustomer(invoice.getCustomer());
		invoiceJRBean.setListInvoiceArticle(listSubInvoice);
		invoiceJRBean.setTotalAmount(invoice.getTotalAmountToPaid());
		invoiceJRBean.setTotalPaid(invoice.getDeposit());
		if(invoice.getTotalAmountToPaid() != null && invoice.getDeposit() != null)
			invoiceJRBean.setTotalRemain(invoice.getTotalAmountToPaid()-invoice.getDeposit());
		//////////////////////////////
		list.add(invoiceJRBean);
		return list;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	@Override
	public String getSubPathTemplate() {
		// TODO Auto-generated method stub
		return "/invoice";
	}
	@Override
	public String getReportFileName() {
		// TODO Auto-generated method stub
		return "/InvoicePrinter.jrxml";
	}
	
}
