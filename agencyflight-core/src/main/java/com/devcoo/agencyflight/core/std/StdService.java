package com.devcoo.agencyflight.core.std;

import java.util.List;


public interface StdService<T extends StdEntity>{
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T find(Integer Id);
	public List<T> findAll();
	public List<T> findAllActive();
}
