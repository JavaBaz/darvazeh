package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event, Long, EventRepository> implements EventService {

    public EventServiceImpl(EventRepository repository) {
        super(repository);
    }

    @Override
    public Event getByName(String eventName) {
        return repository.findByName(eventName)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    @Override
    public List<Event> getByEventType(EventType eventType) {
        return repository.findByEventType(eventType);
    }

    @Override
    public List<Event> getByEventDate(LocalDate eventDate) {
        return repository.findByEventDate(eventDate);
    }

    @Override
    public List<Event> getByEventDateBetween(LocalDate startDate, LocalDate endDate) {
        Assert.isTrue(startDate.isBefore(endDate), "Start date must be before end date");
        return repository.findByEventDateBetween(startDate, endDate);
    }


    @Override
    public Page<Event> getByEventCategory(Long eventCategoryId, int size, int page) {
        return repository.findByEventCategory(eventCategoryId, PageRequest.of(page, size));
    }

    @Override
    public List<Event> getByTotalCapacityGreaterThanEqual(long capacity) {
        return repository.findByTotalCapacityGreaterThanEqual(capacity);
    }

    @Override
    public List<Event> getByTotalCapacityLessThan(long capacity) {
        return repository.findByTotalCapacityLessThan(capacity);
    }

}
