package com.devcoo.agencyflight.core.std;

import java.io.Serializable;
import java.util.List;

public interface StdService <EntityType, EntityIDType extends Serializable> extends Service {
	EntityType save(EntityType entity);
	EntityType findOne(EntityIDType identifier);
	List<EntityType> findAll();
	void delete(EntityIDType identifier);
    boolean isNew(EntityType entity);
	Class<EntityType> getEntityType();
	Class<EntityIDType> getEntityIdType();
}
