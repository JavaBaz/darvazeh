package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.event.EventRepository;
import com.github.javabaz.darvazeh.feature.event.EventService;
import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.domin.Tickets;
import com.github.javabaz.darvazeh.ticket.infra.BuyTicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.TicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class BuyTicket {
    private final Tickets tickets;
    private final EventService eventService;

    public TicketResponse addNewTicketByAdmin(TicketRequest ticketRequest) {
        var price = new Price(ticketRequest.getPrice());
        // todo getUserId from JWT , get id after login
//        var ticket = Ticket.createTicket(1L, price, ticketRequest.getStartTime(), ticketRequest.getEventId(), userId);
//
//        return TicketResponse.builder()
//                .Id(id)
//                .eventId(ticket.getEventId())
//                .price(ticket.getPrice().price())
//                .date(ticket.getEnableDateTime())
//                .userId(userId);
        return null;
    }

    public TicketResponse buyTicket(BuyTicketRequest request) {
        var ticket = tickets.ticketBought(request.getTicketId());
        existEvent(request.getEventId());
        Assert.isTrue(tickets.userHasAnyTicket(userId), "you already have ticket ");

        Ticket ticketBought = ticket.buyTicket(request.getTicketId(), request.getPrice(), request.getStartTime());

        return tickets.modifyToBought(ticketBought, userId, request.getEventId())
                .map(ticketEntity -> new TicketResponse(ticketEntity.getId(), ticketEntity.getEventId(),
                        ticketEntity.getPrice(), ticketEntity.getUserId(), ticketEntity.getDateTime()));


    }

    private void existEvent(Long eventId) {
        eventService.getById(eventId);
    }

}
