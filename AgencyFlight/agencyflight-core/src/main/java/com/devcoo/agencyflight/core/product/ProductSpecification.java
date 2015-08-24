package com.devcoo.agencyflight.core.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;

public class ProductSpecification implements Specification<Product> {
	
	private String code;
	private String productName;
	private ProductType productType;
	
	private VisaType visaType;
	private Country nationality;
	private Period period;

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		
		if (code != null) {
			Expression<String> exCode = root.get("code");
			predicates.add(cb.like(cb.lower(exCode), code));
		}
		if (productName != null) {
			Expression<String> exProductName = root.get("productName");
			predicates.add(cb.like(cb.lower(exProductName), productName));
		}
		if (productType != null) {
			Expression<Integer> exProductType = root.get("productType");
			predicates.add(cb.equal(exProductType, productType.getId()));
		}
		
		Path<Visa> pathVisa = root.get("visa");
		if (visaType != null) {
			Expression<VisaType> exVisaType = pathVisa.get("visaType");
			predicates.add(cb.equal(exVisaType, visaType));
		}
		
		if (nationality != null) {
			Expression<Country> exNationality = pathVisa.get("nationality");
			predicates.add(cb.equal(exNationality, nationality));
		}
		
		if (period != null) {
			Expression<Period> exPeriod = pathVisa.get("period");
			predicates.add(cb.equal(exPeriod, period));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
	public void reset() {
		code = null;
		productName = null;
		productType = null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code != null) {
			this.code = "%"+ code.toLowerCase() + "%";;
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		if (productName != null) {
			this.productName = "%"+ productName.toLowerCase() + "%";;
		}
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public VisaType getVisaType() {
		return visaType;
	}

	public void setVisaType(VisaType visaType) {
		this.visaType = visaType;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}
