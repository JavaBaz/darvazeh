package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BuyTicketShould {

    @Test
    public void add_when_have_specified_type_and_price() {
        Price price = new Price(12L);
        Ticket ticket = Ticket.buyTickets(1L, price, 1L, LocalDateTime.now(), 1L);

    }

    @Test
    public void valid_when_user_just_have_one_type_of_ticket_and_one_ticket() {

    }
}