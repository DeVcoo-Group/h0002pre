package com.devcoo.agencyflight.fe.ui.printer;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.report.ReportLayoutGenerator;
import com.vaadin.ui.Table.Align;

public class InvoicePrinter extends ReportLayoutGenerator<InvoiceService, Invoice>{

	private static final long serialVersionUID = 2725250598876938595L;
	public static final String NAME = "fe.invoice.printer";

	private static String TEST_DETAIL_1 = "TEST_1";
	private static String TEST_DETAIL_2 = "TEST_2";
	private static String TEST_DETAIL_3 = "TEST_3";
	private static String TEST_DETAIL_4 = "TEST_4";
	private static String TEST_DETAIL_5 = "TEST_5";
	
	private static String TEST_FOOTER_1 = "FOOTER_1";
	private static String TEST_FOOTER_2 = "FOOTER_2";
	
	public InvoicePrinter() {
		super("invoiceServiceImp");
	}
	
	@Override
	protected ArrayList<ArrayList<String>> buildDataDetail() {
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		for(int j=1;j<=30;j++) {
			ArrayList<String> columns = new ArrayList<String>();
			for(int i=1;i<=5;i++) {
				columns.add("Test_"+i);
			}
			rows.add(columns);
		}
		return rows;
	}
	@Override
	protected ArrayList<ArrayList<String>> buildDataFooter() {
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		for(int j=1;j<=30;j++) {
			ArrayList<String> columns = new ArrayList<String>();
			for(int i=1;i<=2;i++) {
				columns.add("Test_"+i);
			}
			rows.add(columns);
		}
		return rows;
	}
	@Override
	protected List<Column> buildDetailColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(TEST_DETAIL_1, "Id", String.class, Align.LEFT, 50));
		columns.add(new Column(TEST_DETAIL_2, "Product Code", String.class, Align.LEFT, 200));
		columns.add(new Column(TEST_DETAIL_3, "Product Name", String.class, Align.LEFT));
		columns.add(new Column(TEST_DETAIL_4, "Product Type", String.class, Align.LEFT, 200));
		columns.add(new Column(TEST_DETAIL_5, "Price", String.class, Align.RIGHT, 150));
		return columns;
	}
	@Override
	protected List<Column> buildSummaryColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(TEST_FOOTER_1, "", String.class, Align.LEFT, 50));
		columns.add(new Column(TEST_FOOTER_2, "", String.class, Align.LEFT, 200));
		return columns;
	}

	

}
