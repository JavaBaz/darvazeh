package com.github.javabaz.darvazeh.common.base;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID> {

    List<E> findByCreatedAtAfter(LocalDate createdAt);

    List<E> findByUpdatedAtAfter(LocalDate updatedAt);

    List<E> findByCreatedAtBefore(LocalDate createdAt);

    List<E> findByUpdatedAtBefore(LocalDate updatedAt);
}
