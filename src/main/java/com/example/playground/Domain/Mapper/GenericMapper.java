package com.example.playground.Domain.Mapper;

import com.example.playground.Domain.Model.GenericDomain;
import com.example.playground.Infrastructure.DBMS.Entity.GenericEntity;
import com.example.playground.Presentation.DTO.GenericCreationDTO;

public interface GenericMapper<E extends GenericEntity, D extends GenericDomain, C extends GenericCreationDTO> {
    D toDomain(E entity);
    E creationDtoToEntity(C creationDTO);
}
