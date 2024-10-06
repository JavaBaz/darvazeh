package com.github.javabaz.darvazeh.feature.ticket;

import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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
        dateValidation(event.getEventDate(), ticketRequest.getEnabledFrom(), ticketRequest.getEnabledTo());
        TicketEntity ticketEntity = getTicketEntity(ticketRequest, event);
        //todo ticket is saved or not saved yet cascade all amin
        event.getTicketEntities().add(ticketEntity);
        TicketEntity ticketSaved = ticketRepository.save(ticketEntity);


        return TicketResponse.builder()
                .ticketId(ticketSaved.getId())
                .eventResponse(getEventResponse(ticketSaved))
                .price(ticketSaved.getPrice()).build();

    }

    private TicketEntity getTicketEntity(TicketRequest ticketRequest, Event event) {
        return TicketEntity.builder()
                .countOfTicket(getTotalCapacity(event))
                .price(ticketRequest.getPrice())
                .enableDateFrom(ticketRequest.getEnabledFrom())
                .enableDateTo(ticketRequest.getEnabledTo())
                .event(event)
                .build();
    }

    private TicketResponse.EventResponse getEventResponse(TicketEntity ticketSaved) {
        return TicketResponse.EventResponse.builder()
                .eventCategory(ticketSaved.getEvent().getEventCategory())
                .eventType(ticketSaved.getEvent().getEventType())
                .eventDate(ticketSaved.getEvent().getEventDate())
                .location(ticketSaved.getEvent().getLocation())
                .totalCapacity(ticketSaved.getCountOfTicket()).build();
    }

    private void hasValidCapacity(Event event, CreateTicketTypeRequest createTicketTypeRequest) {
        long filledCapacity = event.getTicketTypeEntities().stream()
                .mapToLong(TicketType::getQuantity)
                .sum();

        long remainingCapacity = event.getTotalCapacity() - filledCapacity;

        if (remainingCapacity < createTicketTypeRequest.getQuantity()) {
            throw new IllegalStateException(String.format(
                    "Cannot add %d tickets; remaining capacity for event '%s' is only %d.",
                    createTicketTypeRequest.getQuantity(), event.getName(), remainingCapacity));
        }
    }

    private void dateValidation(LocalDate dateOfEvent, LocalDateTime startSell, LocalDateTime endSell) {
        LocalDateTime eventStartTime = dateOfEvent.atStartOfDay();

        if (endSell == null) {
            endSell = eventStartTime;
        }
        if (startSell == null) {
            startSell = LocalDateTime.now();
        }

        if (startSell.isAfter(eventStartTime)) {
            throw new IllegalStateException("Start sell time cannot be after the event start time.");
        }

        if (endSell.isAfter(eventStartTime)) {
            throw new IllegalStateException("End sell time cannot be after the event start time.");
        }

        if (startSell.isAfter(endSell)) {
            throw new IllegalStateException("Start sell time must be before end sell time.");
        }

    }
}
