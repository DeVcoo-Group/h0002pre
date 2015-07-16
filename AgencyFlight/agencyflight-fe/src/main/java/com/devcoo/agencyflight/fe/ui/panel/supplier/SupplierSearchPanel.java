package com.devcoo.agencyflight.fe.ui.panel.supplier;

import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class SupplierSearchPanel extends AbstractSearchLayout<SupplierService, Supplier> {

	private static final long serialVersionUID = 6991610947954513343L;
	
	private TextField txtName;

	public SupplierSearchPanel() {
		super("supplierServiceImp");
	}

	@Override
	protected Component initGUI() {
		intiControls();
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtName);
		horizontalLayout.addComponent(formLayout);
		
		return horizontalLayout;
	}
	
	private void intiControls() {
		txtName = VaadinFactory.getTextField("Name", 200);
	}

	@Override
	public Iterator<Supplier> getRestrictions() {
		return service.findAll(new SupplierSpecification()).iterator();
	}

	@Override
	public void reset() {
		txtName.setValue("");
	}
	
	private class SupplierSpecification implements Specification<Supplier> {

		@Override
		public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
			String strCode = "%" + txtName.getValue().toLowerCase() + "%";
			Expression<String> code = root.get("name");
			return cb.like(cb.lower(code), strCode);
		}
		
	}

}
