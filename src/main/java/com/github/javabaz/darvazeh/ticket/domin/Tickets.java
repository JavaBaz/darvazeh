package com.github.javabaz.darvazeh.ticket.domin;

import com.github.javabaz.darvazeh.ticket.infra.TicketEntity;

public interface Tickets {


    Ticket addTicket(Ticket ticket, Long userId, Long eventId);

    Ticket getById(Long id);

    boolean exist(Long id);

    boolean userHasAnyTicket(Long userId);

    Ticket ticketBought(Long ticketId);
}
