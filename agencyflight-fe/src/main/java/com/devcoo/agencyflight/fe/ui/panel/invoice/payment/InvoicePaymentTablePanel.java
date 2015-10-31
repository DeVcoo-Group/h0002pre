package com.devcoo.agencyflight.fe.ui.panel.invoice.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.payment.Payment;
import com.devcoo.agencyflight.core.payment.PaymentService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class InvoicePaymentTablePanel extends AbstractListLayout<PaymentService, Payment> {

	private static final long serialVersionUID = -2794666498460289057L;
	
	private static final String ID = "id";
	private static final String EMPLOYEE = "employee";
	private static final String PAYMENT_DATE = "paymentDate";
	private static final String AMOUNT = "amount";

	public InvoicePaymentTablePanel() {
		super("paymentServiceImp");
	}

	@Override
	protected void initGUI() {
		setMargin(false);
		table.setCaption("List Payments");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Payment payment) {
		item.getItemProperty(ID).setValue(payment.getId());
		item.getItemProperty(EMPLOYEE).setValue(payment.getEmployee().getName());
		item.getItemProperty(PAYMENT_DATE).setValue(payment.getCreateDate());
		item.getItemProperty(AMOUNT).setValue(NumberUtil.formatCurrency(payment.getAmount()));
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 100));
		columns.add(new Column(EMPLOYEE, "Employee", String.class, Align.LEFT, 200));
		columns.add(new Column(PAYMENT_DATE, "Payment Date", Date.class, Align.LEFT));
		columns.add(new Column(AMOUNT, "Amount", String.class, Align.RIGHT, 200));
		return columns;
	}
	
	public void assignValues(Invoice invoice) {
		if (invoice != null) {
			buildTableDataSource(service.findByInvoice(invoice).iterator());
		}
	}
	
	@Override
	protected boolean enableSearchPanel() {
		return false;
	}
	
	@Override
	protected AbstractSearchLayout<PaymentService, Payment> buildSearchPanel() {
		return null;
	}

}
