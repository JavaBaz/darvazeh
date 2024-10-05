package com.github.javabaz.darvazeh.ticket.domin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Ticket {
    private Long ticketId;
    private final Price price;
    private LocalDateTime enableDateTime;

    private Ticket(Long ticketId, Price price, LocalDateTime enableDateTime) {
        this.ticketId = ticketId;
        this.price = price;
        if (enableDateTime == null) this.enableDateTime = LocalDateTime.now();
    }

    public static Ticket createTicket(Long ticketId, Price price, LocalDateTime enableDateTime) {
        return new Ticket(ticketId, price, enableDateTime);
    }

    public Ticket buyTicket(Long ticketId, Long price, LocalDateTime time) {
        this.ticketId=ticketId;
        this.price.isEqualOrGreater(price);
        isValidTime(time);
        return new Ticket(this.ticketId, new Price(price), time);
    }

    private void isValidTime(LocalDateTime time) {
        if (enableDateTime.isBefore(time))
            throw new IllegalArgumentException("it's not valid time");
    }

}
