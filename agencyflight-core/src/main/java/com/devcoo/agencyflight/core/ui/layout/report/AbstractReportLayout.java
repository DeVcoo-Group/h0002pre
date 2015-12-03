package com.devcoo.agencyflight.core.ui.layout.report;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@Component
@Scope("prototype")
@Theme("agencyflight")
public abstract class AbstractReportLayout<Service extends StdService<Entity>, Entity extends StdEntity> extends UI{

	private static final long serialVersionUID = 4911587161477335202L;
	
	private VerticalLayout reportContainer;
	private HorizontalLayout pageHeader;
	private HorizontalLayout header;
	private HorizontalLayout detail;
	private HorizontalLayout footer;
	private HorizontalLayout pageFooter;
	
	private Button btnPrint;
	
	public AbstractReportLayout(String serviceName) {
		//super(serviceName);
		reportContainer = new VerticalLayout();
		
		pageHeader = new HorizontalLayout();
		header = new HorizontalLayout();
		detail = new HorizontalLayout();
		footer = new HorizontalLayout();
		pageFooter = new HorizontalLayout();
		
		btnPrint = VaadinFactory.getButtonPrimary("Print");
		btnPrint.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		        JavaScript.getCurrent().execute("print();");
		    }
		});
		reportContainer.addComponent(btnPrint);
		
		pageHeader.addComponent(generatePageHeader());
		reportContainer.addComponent(pageHeader);
		
		header.addComponent(generateHeader());
		reportContainer.addComponent(header);
		
		detail.addComponent(generateDetail());
		reportContainer.addComponent(detail);
		
		footer.addComponent(generateFooter());
		reportContainer.addComponent(footer);
		
		pageFooter.addComponent(generatePageFooter());
		reportContainer.addComponent(pageFooter);
		
		//setContent(reportContainer);
		
	}

	@Override
	protected void init(VaadinRequest request) {
		reportContainer = new VerticalLayout();
		
		pageHeader = new HorizontalLayout();
		header = new HorizontalLayout();
		detail = new HorizontalLayout();
		footer = new HorizontalLayout();
		pageFooter = new HorizontalLayout();
		
		btnPrint = VaadinFactory.getButtonPrimary("Print");
		btnPrint.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		        JavaScript.getCurrent().execute("print();");
		    }
		});
		reportContainer.addComponent(btnPrint);
		
		pageHeader.addComponent(generatePageHeader());
		reportContainer.addComponent(pageHeader);
		
		header.addComponent(generateHeader());
		reportContainer.addComponent(header);
		
		detail.addComponent(generateDetail());
		reportContainer.addComponent(detail);
		
		footer.addComponent(generateFooter());
		reportContainer.addComponent(footer);
		
		pageFooter.addComponent(generatePageFooter());
		reportContainer.addComponent(pageFooter);
		
		setContent(reportContainer);
	}
	
	public VerticalLayout getReportContainer() {
		return reportContainer;
	}

	public void setReportContainer(VerticalLayout reportContainer) {
		this.reportContainer = reportContainer;
	}

	public HorizontalLayout getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(HorizontalLayout pageHeader) {
		this.pageHeader = pageHeader;
	}

	public HorizontalLayout getHeader() {
		return header;
	}

	public void setHeader(HorizontalLayout header) {
		this.header = header;
	}

	public HorizontalLayout getDetail() {
		return detail;
	}

	public void setDetail(HorizontalLayout detail) {
		this.detail = detail;
	}

	public HorizontalLayout getFooter() {
		return footer;
	}

	public void setFooter(HorizontalLayout footer) {
		this.footer = footer;
	}

	public HorizontalLayout getPageFooter() {
		return pageFooter;
	}

	public void setPageFooter(HorizontalLayout pageFooter) {
		this.pageFooter = pageFooter;
	}
	
	public abstract HorizontalLayout generatePageHeader();
	public abstract HorizontalLayout generateHeader();
	public abstract HorizontalLayout generateDetail();
	public abstract HorizontalLayout generateFooter();
	public abstract HorizontalLayout generatePageFooter();
}
