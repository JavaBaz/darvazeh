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
    public Ticket addTicket(Ticket ticket, Long userId, Long eventId) {
        TicketEntity ticketEntity = TicketEntity.builder().eventId(eventId)
                .price(ticket.getPrice().price())
                .dateTime(ticket.getEnableDateTime())
                .userId(userId)
                .build();

        return Optional.of(ticketJpaRepository.save(ticketEntity))
                .map(entity -> Ticket.createTicket(entity.getId(), new Price(entity.getPrice()), entity.getDateTime()))
                .orElseThrow(() -> new IllegalArgumentException("can not create ticket"));
    }

    @Override
    public Optional<TicketEntity> modifyToBought(Ticket ticket, Long userId, Long eventId) {
        var existTicket = getById(ticket.getTicketId());
        TicketEntity ticketEntity = TicketEntity.builder()
                .eventId(eventId)
                .price(ticket.getPrice().price())
                .dateTime(ticket.getEnableDateTime())
                .userId(userId)
                .isBought(true)
                .build();
        ticketEntity.setId(existTicket.getTicketId());

        return Optional.of(ticketJpaRepository.save(ticketEntity));
    }

    @Override
    public Ticket getById(Long id) {
        return ticketJpaRepository.findById(id)
                .map(ticketEntity -> Ticket.createTicket(ticketEntity.getId(), new Price(ticketEntity.getPrice()), ticketEntity.getDateTime()))
                .orElseThrow(() -> new IllegalArgumentException("ticket not exist "));
    }

    @Override
    public boolean exist(Long id) {
        return ticketJpaRepository.findById(id).isPresent();
    }

    @Override
    public boolean userHasAnyTicket(Long userId) {
        return ticketJpaRepository.existsByUserId(userId);

    }

    @Override
    public Ticket ticketBought(Long ticketId) {
        return ticketJpaRepository.findById(ticketId).filter(ticketEntity -> !ticketEntity.isBought())
                .map(ticketEntity -> Ticket.createTicket(ticketEntity.getId(),
                        new Price(ticketEntity.getPrice()), ticketEntity.getDateTime()))
                .orElseThrow(() -> new IllegalStateException("this ticket is bought "));
    }
}
