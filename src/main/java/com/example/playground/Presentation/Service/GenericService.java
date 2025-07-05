package com.example.playground.Presentation.Service;

import com.example.playground.Domain.Mapper.GenericMapper;
import com.example.playground.Domain.Model.GenericDomain;
import com.example.playground.Infrastructure.DBMS.Entity.GenericEntity;
import com.example.playground.Infrastructure.DBMS.Repository.GenericRepository;
import com.example.playground.Presentation.DTO.GenericCreationDTO;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class GenericService<E extends GenericEntity, D extends GenericDomain, C extends GenericCreationDTO> {
    protected final GenericRepository<E> genericRepository;
    protected final GenericMapper<E, D, C> genericMapper;

    public GenericService(GenericRepository<E> genericRepository, GenericMapper<E, D, C> genericMapper) {
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    public List<D> get() {
        var entries = genericRepository.findAll();
        return entries.stream()
                .map(genericMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<D> get(Pageable pageable) {
        Page<E> entries = genericRepository.findAll(pageable);
        return entries.stream()
                .map(genericMapper::toDomain)
                .collect(Collectors.toList());
    }

    public D getById(Long id) {
        return genericRepository.findById(id)
            .map(genericMapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
    }

    public D create(C creationDTO) {
        E entity = genericMapper.creationDtoToEntity(creationDTO);
        E savedEntity = genericRepository.save(entity);
        return genericMapper.toDomain(savedEntity);
    }
}
