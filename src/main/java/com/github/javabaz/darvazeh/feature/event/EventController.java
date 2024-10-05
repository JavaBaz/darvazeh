package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("/{name}")
    public ResponseEntity<Event> getByName(@PathVariable String name) {
        Event eventFound = eventService.getByName(name);
        return new ResponseEntity<>(eventFound, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Event>> getAllByCategories(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @PathVariable Long id) {
        Page<Event> eventCategory = eventService.getByEventCategory(id, page, size);
        return new ResponseEntity<>(eventCategory, HttpStatus.OK);
    }


    @GetMapping
    public List<Event> getByType(@RequestParam EventType eventType) {
        return eventService.getByEventType(eventType);
    }

    @GetMapping
    List<Event> getByDate(@RequestParam LocalDate localDate) {
        return eventService.getByEventDate(localDate);
    }

    @GetMapping
    List<Event> getByDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return eventService.getByEventDateBetween(startDate, endDate);
    }

    @GetMapping
    List<Event> getCapacityGreaterThanEqual(@RequestParam long capacity) {
        return eventService.getByTotalCapacityGreaterThanEqual(capacity);
    }

    @GetMapping
    List<Event> getByCapacityLessThan(@RequestParam long capacity) {
        return eventService.getByTotalCapacityLessThan(capacity);
    }
}
