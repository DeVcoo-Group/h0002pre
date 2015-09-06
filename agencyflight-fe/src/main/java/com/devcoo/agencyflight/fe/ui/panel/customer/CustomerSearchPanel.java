package com.devcoo.agencyflight.fe.ui.panel.customer;

import java.io.File;
import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.customer.ReportCustomerList;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class CustomerSearchPanel extends AbstractSearchLayout<CustomerService, Customer> {

	private static final long serialVersionUID = 6991610947954513343L;
	
	private TextField txtCode;
	private TextField txtFirstName;
	private TextField txtLastName;
	private Button btnGenerateReport;
	private Button btnPrint;
	
	public CustomerSearchPanel() {
		super("customerServiceImp");
	}

	@Override
	protected Component initGUI() {
		intiControls();
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtFirstName);
		formLayout.addComponent(txtLastName);
		horizontalLayout.addComponent(formLayout);
		
		formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		horizontalLayout.addComponent(formLayout);
		horizontalLayout.addComponent(btnGenerateReport);
		horizontalLayout.addComponent(btnPrint);
		btnPrint.setVisible(false);
		
//		VaadinSession.getCurrent().addRequestHandler(new RequestHandler() {
//			@Override
//			public boolean handleRequest(VaadinSession session,
//					VaadinRequest request,
//					VaadinResponse response) throws IOException {
//				response.setHeader("Content-Type", "text/html; charset=UTF-8");
//				ReportCustomerList report = new ReportCustomerList(service.findAll(new CustomerSpecification()));
//				response.getOutputStream().write(report.generateReport());;
//				return true;
//			}
//		});
		
		btnGenerateReport.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = -3196097681513221180L;

			public void buttonClick(ClickEvent event) {
				ReportCustomerList report = new ReportCustomerList(service.findAll(new CustomerSpecification()));
				File f = report.generateReport();
				f.deleteOnExit();
				Resource fr= new FileResource(f);
				BrowserWindowOpener bwo = new BrowserWindowOpener(fr);
				bwo.extend(btnPrint);
				btnPrint.setVisible(true);
				//Window win = new Window();
				//fr.getStream();
				
//			    ResourceReference rr = ResourceReference.create(fr, UI.getCurrent(), "help");
//			    Page.getCurrent().open(rr.getURL(), "blank_");
			    
			    
				//Page.getCurrent().open(f.getAbsolutePath(), "Report");
				//UI.getCurrent().addWindow(win);
			}
		});
		return horizontalLayout;
	}
	
	private void intiControls() {
		txtCode = VaadinFactory.getTextField("Code", 200);
		txtFirstName = VaadinFactory.getTextField("First name", 200);
		txtLastName = VaadinFactory.getTextField("Last name", 200);
		btnPrint = VaadinFactory.getButtonPrimary("Print");
		btnGenerateReport = VaadinFactory.getButton("Generate Report");
	}

	@Override
	public Iterator<Customer> getRestrictions() {
		return service.findAll(new CustomerSpecification()).iterator();
	}

	@Override
	public void reset() {
		txtCode.setValue("");
		txtFirstName.setValue("");
		txtLastName.setValue("");
	}
	
	private class CustomerSpecification implements Specification<Customer> {

		@Override
		public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
			String strCode = "%" + txtCode.getValue() + "%";
			Expression<String> code = root.get("code");
			
			String strFirstName = "%" + txtFirstName.getValue() + "%";
			Expression<String> firstName = root.get("firstName");
			
			String strLastName = "%" + txtLastName.getValue() + "%";
			Expression<String> lastName = root.get("lastName");
			
			return cb.and(
					cb.like(cb.lower(code), strCode),
					cb.like(cb.lower(firstName), strFirstName),
					cb.like(cb.lower(lastName), strLastName));
		}
		
	}

}
