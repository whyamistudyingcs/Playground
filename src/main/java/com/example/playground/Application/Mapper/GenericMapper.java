package com.example.playground.Application.Mapper;

import com.example.playground.Domain.Model.GenericDomain;
import com.example.playground.Infrastructure.DBMS.Entity.GenericEntity;

public interface GenericMapper<E extends GenericEntity, D extends GenericDomain> {
    D toDomain(E entity);
}
