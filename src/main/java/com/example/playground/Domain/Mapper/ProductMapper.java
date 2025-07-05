package com.example.playground.Domain.Mapper;

import com.example.playground.Presentation.DTO.ProductCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.playground.Domain.Model.ProductDomain;
import com.example.playground.Infrastructure.DBMS.Entity.ProductEntity;

@Mapper
public interface ProductMapper extends GenericMapper<ProductEntity, ProductDomain, ProductCreationDTO> {
    @Override
    ProductDomain toDomain(ProductEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    ProductEntity creationDtoToEntity(ProductCreationDTO creationDTO);
}
