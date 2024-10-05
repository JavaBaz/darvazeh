package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.common.auth.jwt.JwtUser;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventService;
import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.domin.Tickets;
import com.github.javabaz.darvazeh.ticket.infra.TicketEntity;
import com.github.javabaz.darvazeh.ticket.infra.TicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddTicketByOrganization {
    private final Tickets tickets;
    private final EventService eventService;


    public TicketResponse addTicket(TicketRequest ticketRequest) {

        Long price = ticketRequest.getPrice();
        Ticket ticket = Ticket.createTicket(null, new Price(price), ticketRequest.getStartTime());
        Event event = eventService.getById(ticketRequest.getEventId());
        Long userId = JwtUser.getAuthenticatedUser().getId();
        return tickets.addTicket(ticket, userId, event.getId())
                .map(ticketEntity -> getTicketResponse(ticketEntity, event))
                .orElseThrow(() -> new IllegalStateException("Could not add ticket"));
    }

    private static TicketResponse getTicketResponse(TicketEntity ticketEntity, Event event) {
        return TicketResponse.builder().Id(ticketEntity.getId())
                .date(ticketEntity.getDateTime())
                .price(ticketEntity.getPrice())
                .eventId(event.getId())
                .userId(ticketEntity.getUserId()).build();
    }
}
