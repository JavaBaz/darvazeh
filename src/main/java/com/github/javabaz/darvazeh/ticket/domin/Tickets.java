package com.github.javabaz.darvazeh.ticket.domin;

public interface Tickets {


    Ticket addTicket(Ticket ticket, Long userId, Long eventId);

    boolean exist(Long id);

    boolean userHasAnyTicket(Long userId);
}
