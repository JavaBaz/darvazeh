package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.common.auth.jwt.JwtUser;
import com.github.javabaz.darvazeh.feature.event.EventService;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.domin.Tickets;
import com.github.javabaz.darvazeh.ticket.infra.presentation.dto.BuyTicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.TicketEntity;
import com.github.javabaz.darvazeh.ticket.infra.presentation.dto.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class BuyTicket {
    private final Tickets tickets;
    private final EventService eventService;


    public TicketResponse buyTicket(BuyTicketRequest request) {
        var ticket = tickets.ticketBought(request.getTicketId());
        existEvent(request.getEventId());
        Long userId = JwtUser.getAuthenticatedUser().getId();
        Assert.isTrue(tickets.userHasAnyTicket(userId), "you already have ticket ");

        Ticket ticketBought = ticket.buyTicket(request.getTicketId(), request.getPrice(), request.getStartTime());

        return tickets.modifyToBought(ticketBought, userId, request.getEventId())
                .map(entity -> buildTicketResponse(request, entity))
                .orElseThrow(() -> new IllegalStateException("Invalid ticket"));

    }

    private static TicketResponse buildTicketResponse(BuyTicketRequest request, TicketEntity entity) {
        return TicketResponse.builder().Id(entity.getId())
                .date(entity.getDateTime())
                .price(entity.getPrice())
                .eventId(request.getEventId())
                .userId(entity.getUserId()).build();
    }

    private void existEvent(Long eventId) {
        eventService.getById(eventId);
    }

}
