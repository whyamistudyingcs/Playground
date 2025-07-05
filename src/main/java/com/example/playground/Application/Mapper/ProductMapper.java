package com.example.playground.Application.Mapper;

import org.mapstruct.Mapper;

import com.example.playground.Domain.Model.ProductDomain;
import com.example.playground.Infrastructure.DBMS.Entity.ProductEntity;

@Mapper
public interface ProductMapper extends GenericMapper<ProductEntity, ProductDomain> {
    @Override
    ProductDomain toDomain(ProductEntity entity);
}
