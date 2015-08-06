package com.devcoo.agencyflight.core.product.visa.type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class VisaTypeSpecification implements Specification<VisaType> {
	
	private String code;

	@Override
	public Predicate toPredicate(Root<VisaType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		
		if (code != null) {
			Expression<String> exCode = root.get("code");
			predicates.add(cb.like(cb.lower(exCode), code));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code != null) {
			this.code = "%"+ code.toLowerCase() + "%";
		}
	}

}
