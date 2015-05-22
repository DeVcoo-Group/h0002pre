package com.devcoo.agencyflight.core.std;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
public abstract class StdServiceImp<SampleRepository extends StdDao<T>, T extends StdEntity> implements StdService<T>{
	@Autowired
	@Resource
	protected SampleRepository dao;
	
	public void save(T entity) {
		entity.setModifyDate(new Date());
		entity.setCreateDate(new Date());
		entity.setActive(true);
		dao.save(entity);
	}
	
	public void update(T entity) {
		entity.setModifyDate(new Date());
		dao.save(entity);
	}
	
	public void delete(T entity) {
		entity.setActive(false);
		dao.save(entity);
	}
}
