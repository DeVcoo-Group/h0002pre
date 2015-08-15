package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class InvoiceTablePanel extends AbstractListLayout<InvoiceService, Invoice> {

	private static final long serialVersionUID = 1940622554822134460L;
	
	private static final String ID = "id";
	private static final String CODE = "code";
	private static final String CUSTOMER = "customer";
	private static final String EMPLOYEE = "employee";
	private static final String DEPOSIT = "deposit";
	private static final String AMOUNT_RECEIVE = "amountReceive";

	public InvoiceTablePanel() {
		super("invoiceServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Invoices");
		buildDefaultCRUDBar();
		table.setCaption("List Invoices");
	}

	@Override
	protected InvoiceSearchPanel buildSearchPanel() {
		return new InvoiceSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Invoice entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(CODE).setValue(entity.getCode());
		item.getItemProperty(CUSTOMER).setValue(entity.getCustomer().getFirstName() + entity.getCustomer().getLastName());
		item.getItemProperty(EMPLOYEE).setValue(entity.getEmployee().getName());
		item.getItemProperty(DEPOSIT).setValue(NumberUtil.formatCurrency(entity.getDeposit()));
		item.getItemProperty(AMOUNT_RECEIVE).setValue(NumberUtil.formatCurrency(entity.getAmountReceive()));
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 100));
		columns.add(new Column(CODE, "Invoice Code", String.class, Align.LEFT));
		columns.add(new Column(CUSTOMER, "Customer Name", String.class, Align.LEFT, 200));
		columns.add(new Column(EMPLOYEE, "Employee", String.class, Align.LEFT, 200));
		columns.add(new Column(DEPOSIT, "Deposit", String.class, Align.RIGHT, 200));
		columns.add(new Column(AMOUNT_RECEIVE, "Amount receive", String.class, Align.RIGHT, 200));
		return columns;
	}

}
