package com.github.javabaz.darvazeh.common.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e);

    E update(E e);

    void delete(E e);

    E getById(ID id);

    List<E> getAll();

    boolean existsById(ID id);

    void deleteById(ID id);

    List<E> getAllCreatedAfter(LocalDate createdAt);
    List<E> getAllUpdatedAfter(LocalDate updatedAt);
    List<E> getAllCreatedBefore(LocalDate createdAt);
    List<E> getAllUpdatedBefore(LocalDate updatedAt);
}
