package com.github.javabaz.darvazeh.ticket.domin.usecase;

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
        Assert.isTrue(!tickets.exist(request.getTicketId()), "this ticket not exist ");
//        Assert.isTrue(tickets.userHasAnyTicket(userId), "you already have ticket ");
        return null;

    }

}
