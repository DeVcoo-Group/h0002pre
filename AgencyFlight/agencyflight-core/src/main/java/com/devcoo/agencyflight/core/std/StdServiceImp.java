package com.devcoo.agencyflight.core.std;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.user.User;
import com.vaadin.ui.UI;
public abstract class StdServiceImp<SampleRepository extends StdDao<T>, T extends StdEntity> implements StdService<T>{
	@Autowired
	protected SampleRepository dao;
	protected WebContext context;
	
	public List<T> findAll() {
		return dao.findAll();
	}
	public List<T> findAllActive() {
		return dao.findAll(new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				return cb.equal(root.get("active"), true);
			}
		});
	}
	
	public T find(Integer id) {
		return dao.findOne(id);
	}
	
	public void save(T entity) {
		entity.setModifyDate(new Date());
		entity.setCreateDate(new Date());
		entity.setActive(true);
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	
	public void update(T entity) {
		entity.setModifyDate(new Date());
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	
	public void delete(T entity) {
		entity.setActive(false);
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	public User getLogUser() {
		context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		if(context == null) {
			context = new WebContext();
		}
		if(context.getLog_user() == null) {
			context.setLog_user(new User());
		}
		return context.getLog_user();
	}
}
