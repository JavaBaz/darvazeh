package com.github.javabaz.darvazeh.feature.ticket.tickettype;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class TicketTypeService extends BaseServiceImpl<TicketType, Long, TicketTypeRepository> {

    private final EventService eventService;

    public TicketTypeService(EventService eventService, TicketTypeRepository repository) {
        super(repository);
        this.eventService = eventService;
    }

    @Transactional(rollbackFor = Exception.class)
    public CreateTicketTypeResponse addNewTicket(CreateTicketTypeRequest createTicketTypeRequest) {
        Event event = eventService.getById(createTicketTypeRequest.getEventId());

        hasValidCapacity(event, createTicketTypeRequest);
        dateValidation(event.getEventDate(), createTicketTypeRequest.getEnabledFrom(), createTicketTypeRequest.getEnabledTo());

        TicketType ticketType = getTicketEntity(createTicketTypeRequest, event);
        event.getTicketTypeEntities().add(ticketType);

        TicketType ticketTypeSaved = repository.save(ticketType);


        return CreateTicketTypeResponse.builder()
                .ticketId(ticketTypeSaved.getId())
                .eventResponse(getEventResponse(ticketTypeSaved))
                .price(ticketTypeSaved.getPrice()).build();

    }

    private TicketType getTicketEntity(CreateTicketTypeRequest createTicketTypeRequest, Event event) {
        return TicketType.builder()
                .quantity(createTicketTypeRequest.getQuantity())
                .price(createTicketTypeRequest.getPrice())
                .enableDateFrom(createTicketTypeRequest.getEnabledFrom())
                .enableDateTo(createTicketTypeRequest.getEnabledTo())
                .event(event)
                .build();
    }

    private CreateTicketTypeResponse.EventResponse getEventResponse(TicketType ticketTypeSaved) {
        return CreateTicketTypeResponse.EventResponse.builder()
                .eventCategory(ticketTypeSaved.getEvent().getEventCategory())
                .eventType(ticketTypeSaved.getEvent().getEventType())
                .eventDate(ticketTypeSaved.getEvent().getEventDate())
                .location(ticketTypeSaved.getEvent().getLocation())
                .totalCapacity(ticketTypeSaved.getQuantity()).build();
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
