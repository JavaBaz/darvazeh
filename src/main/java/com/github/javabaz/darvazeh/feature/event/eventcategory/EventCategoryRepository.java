package com.github.javabaz.darvazeh.feature.event.eventcategory;

import com.github.javabaz.darvazeh.common.base.BaseRepository;


import java.util.Optional;

public interface EventCategoryRepository extends BaseRepository<EventCategory, Long> {
    Optional<EventCategory> findByCategoryName(String name);
}