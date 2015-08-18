package com.devcoo.agencyflight.core.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<Product> {
	
	private String code;
	private String productName;
	private Integer productType;

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
			predicates.add(cb.equal(exProductType, productType));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
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

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

}
