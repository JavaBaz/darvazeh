package com.github.javabaz.darvazeh.ticket.domin;

import com.github.javabaz.darvazeh.ticket.infra.TicketEntity;

import java.util.Optional;

public interface Tickets {


    Optional<TicketEntity>  addTicket(Ticket ticket, Long userId, Long eventId);


    Optional<TicketEntity> modifyToBought(Ticket ticket, Long userId, Long eventId);

    Ticket getById(Long id);

    boolean exist(Long id);

    boolean userHasAnyTicket(Long userId);

    Ticket ticketBought(Long ticketId);
}
