package com.github.javabaz.darvazeh.ticket.infra;

import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.domin.Tickets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketsJpaImplement implements Tickets {
    private final TicketJpaRepository ticketJpaRepository;

    @Override
    public Ticket addTicket(Ticket ticket) {
        TicketEntity ticketEntity = TicketEntity.builder().eventId(ticket.getEventId())
                .price(ticket.getPrice().price())
                .dateTime(ticket.getEnableDateTime())
                .userId(ticket.getUserId())
                .build();

        return Optional.of(ticketJpaRepository.save(ticketEntity))
                .map(entity -> Ticket.createTicket(entity.getId(), new Price(entity.getPrice())
                        , entity.getEventId(), entity.getDateTime(), entity.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("can not create ticket"));
    }

    @Override
    public boolean exist(Long id) {
        return ticketJpaRepository.findById(id).isPresent();
    }

    @Override
    public boolean userHasAnyTicket(Long userId) {
        return ticketJpaRepository.existsByUserId(userId);

    }
}
