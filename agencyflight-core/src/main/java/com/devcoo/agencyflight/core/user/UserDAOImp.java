package com.devcoo.agencyflight.core.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;



@Repository
public class UserDAOImp implements UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public User getAuthoriseUser(String name, String password) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		List<Predicate> p = new ArrayList<Predicate>();
		p.add(builder.equal(root.get("name"),name));
		p.add(builder.equal(root.get("password"), password));
		cq.select(root);
		cq.where(builder.and(p.toArray(new Predicate[p.size()])));
		return entityManager.createQuery(cq).getSingleResult();
	}
	
}
