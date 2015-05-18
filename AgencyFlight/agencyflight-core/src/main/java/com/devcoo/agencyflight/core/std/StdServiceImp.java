package com.devcoo.agencyflight.core.std;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StdServiceImp <EntityType, EntityIDType extends Serializable, CrudRepositoryType extends StdDao<EntityType, EntityIDType>>
implements StdService<EntityType,EntityIDType> {
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
    @PersistenceContext
    private EntityManager entityManager;
	
	protected CrudRepositoryType repository;
	
	private Class<EntityType> entityType;
	private Class<EntityIDType> entityIdType;
	
	public EntityType save(EntityType entity) {
		return repository.save(entity);
	}

    
	public EntityType findOne(EntityIDType identifier) {
		return repository.findOne(identifier);
	}

    
	public List<EntityType> findAll() {
		return repository.findAll();
	}

	
	public void delete(EntityIDType identifier) {
		repository.delete(identifier);
	}
	public Class<EntityType> getEntityType() {
		return entityType;
	}

	public Class<EntityIDType> getEntityIdType() {
		return entityIdType;
	}

	@SuppressWarnings("unchecked")
	public boolean isNew(EntityType entity) {
		return ((StdService<EntityType, EntityIDType>) repository).isNew(entity);
	}


}
