package com.github.javabaz.darvazeh.feature.ticket;

import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketTypeResponse {
    private long ticketId;
    private long price;
    private EventResponse eventResponse;

    @Builder
    static class EventResponse {
        private EventCategory eventCategory;
        private EventType eventType;
        private String location;
        private long totalCapacity;
        private LocalDate eventDate;
    }

}
