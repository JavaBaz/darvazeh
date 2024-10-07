package com.github.javabaz.darvazeh.feature.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    void shouldThrowExceptionWhenStartAfterEnd() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 12, 31);
        LocalDate endDate = LocalDate.of(2023, 1, 1);

        // Act & Assert
        assertThatThrownBy(() -> eventService.getByEventDateBetween(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Start date must be before end date");
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