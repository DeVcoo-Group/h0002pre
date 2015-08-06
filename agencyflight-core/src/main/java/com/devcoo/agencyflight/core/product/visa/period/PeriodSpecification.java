package com.devcoo.agencyflight.core.product.visa.period;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PeriodSpecification implements Specification<Period> {
	
	private String day;

	@Override
	public Predicate toPredicate(Root<Period> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		
		if (day != null) {
			Expression<String> exDays = root.get("day");
			predicates.add(cb.like(cb.lower(exDays), day));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		if (day != null) {
			this.day = "%"+ day.toLowerCase() + "%";
		}
	}

}
