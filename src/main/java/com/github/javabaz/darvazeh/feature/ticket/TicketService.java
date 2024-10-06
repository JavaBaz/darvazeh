package com.github.javabaz.darvazeh.feature.ticket;

import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {
    private final EventService eventService;
    private final TicketRepository ticketRepository;

    @Transactional(rollbackFor = Exception.class)
    public TicketResponse add(TicketRequest ticketRequest) {
        Event event = eventService.getById(ticketRequest.getEventId());
        hasValidCapacity(event);
        TicketEntity ticketEntity = TicketEntity.builder()
                .totalCapacity(getTotalCapacity(event))
                .price(ticketRequest.getPrice())
                .enableDateFrom(isValidDate(ticketRequest.getEnabledFrom()))
                .enableDateTo(isValidDate(ticketRequest.getEnabledTo()))
                .event(event).build();
        //todo ticket is saved or not saved yet cascade all
        event.setTicketEntities(List.of(ticketEntity));
        TicketEntity ticketSaved = ticketRepository.save(ticketEntity);


        return TicketResponse.builder()
                .ticketId(ticketSaved.getId())
                .eventResponse(getEventResponse(ticketSaved))
                .price(ticketSaved.getPrice()).build();

    }

    private TicketResponse.EventResponse getEventResponse(TicketEntity ticketSaved) {
        return TicketResponse.EventResponse.builder()
                .eventCategory(ticketSaved.getEvent().getEventCategory())
                .eventType(ticketSaved.getEvent().getEventType())
                .eventDate(ticketSaved.getEvent().getEventDate())
                .location(ticketSaved.getEvent().getLocation())
                .totalCapacity(ticketSaved.getTotalCapacity()).build();
    }

    private void hasValidCapacity(Event event) {
        if (event.getTicketEntities().size() <= event.getTotalCapacity())
            throw new IllegalStateException("you can not add new ticket");
    }

    private LocalDateTime isValidDate(LocalDateTime enableDateFrom) {
        if (enableDateFrom != null && enableDateFrom.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("your data is not valid");
        return enableDateFrom;
    }

    private int getTotalCapacity(Event event) {
        try {
            return (int) event.getTotalCapacity();
        } catch (Exception exception) {
            throw new IllegalArgumentException("number is very big ");
        }

    }
}
