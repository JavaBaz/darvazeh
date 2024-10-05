package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event getByName(String eventName);

    List<Event> getByEventType(EventType eventType);

    List<Event> getByEventDate(LocalDate eventDate);

    List<Event> getByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<Event> getByEventCategory(EventCategory eventCategory);

    List<Event> getByTotalCapacityGreaterThanEqual(long capacity);

    List<Event> getByTotalCapacityLessThan(long capacity);
}
