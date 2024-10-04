package com.github.javabaz.darvazeh.feature.event.eventcategory;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;

import java.util.Optional;

public class EventCategoryServiceImpl extends BaseServiceImpl<EventCategory, Long, EventCategoryRepository> {

    public EventCategoryServiceImpl(EventCategoryRepository repository) {
        super(repository);
    }

    public Optional<EventCategory> getByName(String name) {
        return repository.findByCategoryName(name);
    }


}
