package com.github.javabaz.darvazeh.feature.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
