package com.github.javabaz.darvazeh.feature.event.eventcategory;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;


import java.util.Optional;

public interface EventCategoryRepository extends BaseEntityRepository<EventCategory, Long>{
    Optional<EventCategory> findByCategoryName(String name);
}