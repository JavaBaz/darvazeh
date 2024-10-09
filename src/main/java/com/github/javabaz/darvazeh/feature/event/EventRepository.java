package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseRepository;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends BaseRepository<Event, Long> {
    Optional<Event> findByName(String eventName);

    List<Event> findByEventType(EventType eventType);

    List<Event> findByEventDate(LocalDate eventDate);

    List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    //todo change it
//    Page<Event> findByEventCategory(EventCategory eventCategory);
    Page<Event> findByEventCategory(Long eventCategoryId, PageRequest of);

    List<Event> findByTotalCapacityGreaterThanEqual(long capacity);

    List<Event> findByTotalCapacityLessThan(long capacity);


}
