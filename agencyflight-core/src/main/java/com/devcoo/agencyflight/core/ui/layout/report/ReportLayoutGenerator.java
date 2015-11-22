package com.devcoo.agencyflight.core.ui.layout.report;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.vaadin.ui.HorizontalLayout;

public abstract class ReportLayoutGenerator<Service extends StdService<Entity>, Entity extends StdEntity> extends AbstractReportLayout<Service,Entity> {

	private static final long serialVersionUID = 891440734236217470L;
	
	private ReportDetailTable detailTable;
	private ReportFooterTable summaryTable;
	private ArrayList<ArrayList<String>> detailList;
	private ArrayList<ArrayList<String>> summaryList;
	
	

	public ReportLayoutGenerator(String serviceName) {
		super(serviceName);
		buildTableDataSource();
		
	}

	@Override
	public HorizontalLayout generatePageHeader() {
		HorizontalLayout pageHeader = new HorizontalLayout();
		return pageHeader;
	}

	@Override
	public HorizontalLayout generateHeader() {
		HorizontalLayout header = new HorizontalLayout();
		return header;
	}

	public ReportDetailTable getDetailTable() {
		return detailTable;
	}

	public void setDetailTable(ReportDetailTable detailTable) {
		this.detailTable = detailTable;
	}

	public ReportFooterTable getSummaryTable() {
		return summaryTable;
	}

	public void setSummaryTable(ReportFooterTable summaryTable) {
		this.summaryTable = summaryTable;
	}

	public ArrayList<ArrayList<String>> getDetailList() {
		return detailList;
	}

	public void setDetailList(ArrayList<ArrayList<String>> detailList) {
		this.detailList = detailList;
	}

	public ArrayList<ArrayList<String>> getSummaryList() {
		return summaryList;
	}

	public void setSummaryList(ArrayList<ArrayList<String>> summaryList) {
		this.summaryList = summaryList;
	}

	@Override
	public HorizontalLayout generateDetail() {
		HorizontalLayout detail = new HorizontalLayout();
		detailTable = new ReportDetailTable("");
		detailTable.addColumns(buildDetailColumns());
		detail.addComponent(detailTable);
		return detail;
	}

	@Override
	public HorizontalLayout generateFooter() {
		HorizontalLayout footer = new HorizontalLayout();
		summaryTable = new ReportFooterTable("Summary");
		summaryTable.addColumns(buildSummaryColumns());
		footer.addComponent(summaryTable);
		return footer;
	}

	@Override
	public HorizontalLayout generatePageFooter() {
		HorizontalLayout pageFooter = new HorizontalLayout();
		return pageFooter;
	}
	
	protected void buildTableDataSource() {
		detailTable.removeAllItems();
		summaryTable.removeAllItems();
		
		detailList = buildDataDetail();
		summaryList = buildDataFooter();
		
		for (ArrayList<String> row : detailList) {
			if(!row.isEmpty()) {
				detailTable.addItem(row.toArray());
			}
		}
		
		for (ArrayList<String> row : summaryList) {
			if(!row.isEmpty()) {
				summaryTable.addItem(row.toArray());
			}
		}
	}
	
	protected abstract ArrayList<ArrayList<String>> buildDataDetail();
	protected abstract ArrayList<ArrayList<String>> buildDataFooter();
	protected abstract List<Column> buildDetailColumns();
	protected abstract List<Column> buildSummaryColumns();
}
