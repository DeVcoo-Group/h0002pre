package com.devcoo.agencyflight.core.std;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class StdDaoImp<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
	private final EntityManager entityManager;
	
	public StdDaoImp(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}	
	

	@Override
	public T findOne(ID id) {
		Assert.notNull(id, "The given id must not be null!");
		Class<T> domainType = getDomainClass();
		return entityManager.find(domainType, id);
	}
    @Transactional
    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        List<S> savedEntities = super.save(entities);
        return savedEntities;
    }

    @Transactional
    @Override
	public void delete(T entity) {
		super.delete(entity);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		TypedQuery<T> query = getQuery(spec, pageable);
		return pageable == null ? new PageImpl<T>(query.getResultList()) : readPage(query, pageable, spec);
	}
}