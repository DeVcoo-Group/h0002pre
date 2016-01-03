package com.devcoo.agencyflight.core.invoice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.user.User;

public class InvoiceSpecification implements Specification<Invoice> {
	
	private String code;
	private User employee;
	private Customer customer;

	@Override
	public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.isFalse(root.<Boolean>get("delete")));
		cq.orderBy(cb.desc(root.get("id")));
		
		if (code != null) {
			Expression<String> exCode = root.get("code");
			predicates.add(cb.like(cb.lower(exCode), code));
		}
		if (employee != null) {
			predicates.add(cb.equal(root.get("employee"), employee));
		}
		if (customer != null) {
			predicates.add(cb.equal(root.get("customer"), customer));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null || code.isEmpty()) this.code = null;
		this.code = "%"+ code.toLowerCase() + "%";
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
