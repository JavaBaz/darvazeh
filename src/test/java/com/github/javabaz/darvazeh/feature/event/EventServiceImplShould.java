package com.github.javabaz.darvazeh.feature.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EventServiceImplShould {
    @Mock
    private EventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventService = new EventServiceImpl(eventRepository);
    }

    @Test
    void findEventByName() {
        Event value = new Event();
        value.setName("JUG");
        when(eventRepository.findByName(anyString())).thenReturn(Optional.of(value));
        Event event = eventService.getByName("JUG");
        Assertions.assertThat(event.getName()).isEqualTo("JUG");
    }
}