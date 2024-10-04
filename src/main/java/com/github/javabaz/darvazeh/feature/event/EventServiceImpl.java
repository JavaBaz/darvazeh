package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl extends BaseServiceImpl<Event,Long,EventRepository> {

    public EventServiceImpl(EventRepository repository) {
        super(repository);
    }

    public Optional<Event> getByName(String eventName) {
        return repository.findByName(eventName);
    }

    public List<Event> getByEventType(EventType eventType) {
        return repository.findByEventType(eventType);
    }

    public List<Event> getByEventDate(LocalDate eventDate) {
        return repository.findByEventDate(eventDate);
    }

    public List<Event> getByEventDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findByEventDateBetween(startDate, endDate);
    }

    public List<Event> getByEventCategory(EventCategory eventCategory) {
        return repository.findByEventCategory(eventCategory);
    }

    public List<Event> getByTotalCapacityGreaterThanEqual(long capacity) {
        return repository.findByTotalCapacityGreaterThanEqual(capacity);
    }

    public List<Event> getByTotalCapacityLessThan(long capacity) {
        return repository.findByTotalCapacityLessThan(capacity);
    }

}
