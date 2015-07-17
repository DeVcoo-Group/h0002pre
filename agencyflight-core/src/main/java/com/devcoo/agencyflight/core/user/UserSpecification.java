package com.devcoo.agencyflight.core.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {
	
	private String name;
	private Integer role;
	
	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		
		if (name != null && !name.isEmpty()) {
			Expression<String> exName = root.get("name");
			predicates.add(cb.like(cb.lower(exName), name));
		}
		
		if (role != null) {
			Expression<Integer> exRole = root.get("role");
			predicates.add(cb.equal(exRole, role));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "%"+ name.toLowerCase() + "%";
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
}