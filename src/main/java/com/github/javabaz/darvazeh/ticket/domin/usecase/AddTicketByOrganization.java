package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventService;
import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.domin.Tickets;
import com.github.javabaz.darvazeh.ticket.infra.TicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddTicketByOrganization {
    private final Tickets tickets;
    private final EventService eventService;


    public TicketResponse addTicket(TicketRequest ticketRequest) {

        Ticket ticket = Ticket.createTicket(null,
                new Price(ticketRequest.getPrice()), ticketRequest.getStartTime());
        Event event = eventService.getById(ticketRequest.getEventId());
        return tickets.addTicket(ticket, userid, event.getId()).map(ticketEntity -> new TicketResponse(ticketEntity.getId(), ticketEntity.getEventId(), ticketEntity.getPrice()
                , ticketEntity.getUserId(), ticketEntity.getDateTime()));
    }
}
