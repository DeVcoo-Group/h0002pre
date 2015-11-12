package com.devcoo.agencyflight.core.std;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;


public interface StdService<T extends StdEntity>{
	public T save(T entity);
	public T saveAndFlush(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T find(Integer Id);
	public T find(Specification<T> query);
	public List<T> findAll(Specification<T> query);
	public List<T> findAll();
	public List<T> findAllNotDelete();
}
