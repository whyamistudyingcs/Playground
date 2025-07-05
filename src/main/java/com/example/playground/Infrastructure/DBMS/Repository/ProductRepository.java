package com.example.playground.Infrastructure.DBMS.Repository;

import org.springframework.stereotype.Repository;

import com.example.playground.Infrastructure.DBMS.Entity.ProductEntity;

@Repository
public interface ProductRepository extends GenericRepository<ProductEntity> {
}
