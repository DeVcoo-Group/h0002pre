package com.devcoo.agencyflight.core.std;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.user.User;
import com.vaadin.ui.UI;
public abstract class StdServiceImp<SampleRepository extends StdDao<T>, T extends StdEntity> implements StdService<T>{
	@Autowired
	protected SampleRepository dao;
	
	public List<T> findAll() {
		return dao.findAll();
	}
	public List<T> findAllNotDelete() {
		return dao.findAll(new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				return cb.equal(root.get("delete"), false);
			}
		});
	}
	
	public List<T> findAll(Specification<T> query) {
		return dao.findAll(query);
	}
	
	public T find(Specification<T> query) {
		return dao.findOne(query);
	}
	
	public T find(Integer id) {
		return dao.findOne(id);
	}
	
	public T save(T entity) {
		entity.setModifyDate(new Date());
		entity.setCreateDate(new Date());
		entity.setDelete(false);
		entity.setLastModifier(getLogUser());
		return dao.save(entity);
	}
	
	public void update(T entity) {
		entity.setModifyDate(new Date());
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	
	public void delete(T entity) {
		entity.setDelete(true);
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	public User getLogUser() {
		return ApplicationContext.getLog_user();
	}
}
