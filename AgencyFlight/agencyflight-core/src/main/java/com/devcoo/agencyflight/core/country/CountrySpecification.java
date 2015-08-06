package com.devcoo.agencyflight.core.country;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CountrySpecification implements Specification<Country> {
	
	private String name;
	private String code2;
	private String code3;

	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		
		if (name != null && !name.isEmpty()) {
			Expression<String> exName = root.get("name");
			predicates.add(cb.like(cb.lower(exName), name));
		}
		
		if (code2 != null && !code2.isEmpty()) {
			Expression<String> exCode2 = root.get("code2");
			predicates.add(cb.like(cb.lower(exCode2), code2));
		}
		
		if (code3 != null && !code3.isEmpty()) {
			Expression<String> exCode3 = root.get("code3");
			predicates.add(cb.like(cb.lower(exCode3), code3));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = "%"+ name.toLowerCase() + "%";
		}
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		if (code2 != null) {
			this.code2 = "%"+ code2.toLowerCase() + "%";
		}
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		if (code3 != null) {
			this.code3 = "%"+ code3.toLowerCase() + "%";
		}
	}
	
}
