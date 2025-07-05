package com.example.playground.Infrastructure.DBMS.Repository;

import com.example.playground.Infrastructure.DBMS.Entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<E extends GenericEntity> extends JpaRepository<E, Long> {
}
