package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends BaseEntityRepository<Event, Long> {
    Optional<Event> findByName(String eventName);
    List<Event> findByEventType(EventType eventType);
    List<Event> findByEventDate(LocalDate eventDate);
    List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
    List<Event> findByEventCategory(EventCategory eventCategory);
    List<Event> findByTotalCapacityGreaterThanEqual(long capacity);
    List<Event> findByTotalCapacityLessThan(long capacity);
}
