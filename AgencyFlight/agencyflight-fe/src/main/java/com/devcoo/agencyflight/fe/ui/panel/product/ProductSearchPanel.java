package com.devcoo.agencyflight.fe.ui.panel.product;

import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.VisaService;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ProductSearchPanel extends AbstractSearchLayout<VisaService, Visa> {

	private static final long serialVersionUID = -8424091792187952568L;
	
	private TextField txtCode;
	private TextField txtName;

	public ProductSearchPanel() {
		super("visaServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtName);
		
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Product Code", 200);
		txtName = VaadinFactory.getTextField("Product Name", 200);
	}

	@Override
	public Iterator<Visa> getRestrictions() {
		return service.findAll(new ProductSpecification()).iterator();
	}

	@Override
	public void reset() {
		txtCode.setValue("");
		txtName.setValue("");
	}
	
	private class ProductSpecification implements Specification<Visa> {

		@Override
		public Predicate toPredicate(Root<Visa> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

			String strCode = "%" + txtCode.getValue() + "%";
			Expression<String> productCode = root.get("code");
			
			String strName = "%" + txtName.getValue() + "%";
			Expression<String> productName = root.get("name");
			
			return cb.and(
					cb.like(cb.lower(productCode), strCode),
					cb.like(cb.lower(productName), strName));
		}
		
	}

}
