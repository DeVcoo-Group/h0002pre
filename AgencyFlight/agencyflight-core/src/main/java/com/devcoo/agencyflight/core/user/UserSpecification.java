package com.devcoo.agencyflight.core.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {
	private String searchText = "";
	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		Predicate p = cq.getRestriction();
		if(!searchText.isEmpty())
		{
			String t_searchText = "%"+ searchText.toLowerCase() + "%";
			Expression<String> userName= root.get("name");
			return cb.like(cb.lower(userName), t_searchText);
		}
		return p;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}