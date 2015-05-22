package com.devcoo.agencyflight.core.std;


public interface StdService<T extends StdEntity>{
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
}
