package com.github.javabaz.darvazeh.common.base;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>,
                                      ID extends Serializable,
                                      R extends BaseEntityRepository<E, ID>
                                     >
                                     implements BaseService<E, ID> {


    protected final R repository;


    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public E save(E e) {
        if (existsById(e.getId()))
            throw new DuplicateKeyException("Entity with same ID already exist");

        else
            return repository.save(e);
    }


    @Override
    public E update(E e) {
        if (repository.existsById(e.getId())) {
            save(e);
            return e;
        }
        throw new EntityNotFoundException("Entity does not exist");
    }

    @Override
    public void delete(E e) {
        repository.delete(e);
    }

    @Override
    public E getById(ID id) {
        Optional<E> e;
        e = repository.findById(id);
        if (e.isEmpty())
            throw new EntityNotFoundException("Entity does not exist");
        return e.get();
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(ID id) {
        if (existsById(id)) {
            repository.deleteById(id);
        }
        throw new EntityNotFoundException("Entity does not exist");
    }

    @Override
    public List<E> getAllCreatedAfter(LocalDate createdAt) {
        return repository.findByCreatedAtAfter(createdAt);
    }

    public List<E> getAllUpdatedAfter(LocalDate updatedAt){
        return repository.findByUpdatedAtAfter(updatedAt);
    }

    public List<E> getAllCreatedBefore(LocalDate createdAt){
        return repository.findByCreatedAtBefore(createdAt);
    }

    public List<E> getAllUpdatedBefore(LocalDate updatedAt){
        return repository.findByUpdatedAtBefore(updatedAt);
    }
}
