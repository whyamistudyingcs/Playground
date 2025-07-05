package com.example.playground.Presentation.Service;

import org.springframework.stereotype.Service;

import com.example.playground.Domain.Mapper.ProductMapper;
import com.example.playground.Domain.Model.ProductDomain;
import com.example.playground.Infrastructure.DBMS.Entity.ProductEntity;
import com.example.playground.Infrastructure.DBMS.Repository.ProductRepository;
import com.example.playground.Presentation.DTO.ProductCreationDTO;

@Service
public class ProductService extends GenericService<ProductEntity, ProductDomain, ProductCreationDTO> {

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
    }

    // Additional methods specific to ProductService can be added here
    
}
