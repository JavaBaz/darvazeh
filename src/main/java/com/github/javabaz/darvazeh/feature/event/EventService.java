package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseService;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public interface EventService extends BaseService<Event, Long> {
    Event getByName(String eventName);

    List<Event> getByEventType(EventType eventType);

    List<Event> getByEventDate(LocalDate eventDate);

    List<Event> getByEventDateBetween(LocalDate startDate, LocalDate endDate);


    Page<Event> getByEventCategory(Long eventCategoryId, int size, int page);

    List<Event> getByTotalCapacityGreaterThanEqual(long capacity);

    List<Event> getByTotalCapacityLessThan(long capacity);
}
