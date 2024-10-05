package com.github.javabaz.darvazeh.common.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;
import java.time.LocalDate;


@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class BaseEntity<ID extends Serializable> implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
