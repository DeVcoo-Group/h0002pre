package com.devcoo.agencyflight.core.std;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface StdDao<T> extends JpaRepository<T, Integer>,JpaSpecificationExecutor<T>{
}
