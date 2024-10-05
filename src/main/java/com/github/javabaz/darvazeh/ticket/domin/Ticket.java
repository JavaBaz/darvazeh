package com.github.javabaz.darvazeh.ticket.domin;

import java.time.LocalDateTime;
import java.util.Objects;


public class Ticket {
    private final Long ticketId;
    private final Price price;
    private final Long eventId;
    private LocalDateTime enableDateTime;
    private final Long userId;

    private Ticket(Long ticketId, Price price, Long eventId, LocalDateTime enableDateTime, Long userId) {
        this.userId = userId;
        if (Objects.isNull(eventId)) throw new IllegalArgumentException("eventId cannot be null");
        this.ticketId = ticketId;
        this.price = price;
        if (enableDateTime == null) this.enableDateTime = LocalDateTime.now();
        this.eventId = eventId;
    }

    public static Ticket buyTickets(Long ticketId, Price price, Long eventId, LocalDateTime enableDateTime, Long userId) {
        return new Ticket(ticketId, price, eventId, enableDateTime, userId);
    }


}
