package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return repository.findByEventDateBetween(startDate, endDate);
    }

    @Override
    public List<Event> getByEventCategory(EventCategory eventCategory) {
        return repository.findByEventCategory(eventCategory);
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
