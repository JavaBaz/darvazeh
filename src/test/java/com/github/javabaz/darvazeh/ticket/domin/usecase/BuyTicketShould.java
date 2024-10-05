package com.github.javabaz.darvazeh.ticket.domin.usecase;

import com.github.javabaz.darvazeh.ticket.domin.Price;
import com.github.javabaz.darvazeh.ticket.domin.Ticket;
import com.github.javabaz.darvazeh.ticket.infra.BuyTicketRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BuyTicketShould {

    @Test
    public void add_when_have_specified_type_and_price() {
//        var price = new Price(12L);
//        var ticket = Ticket.createTicket(1L, price, LocalDateTime.now());
//        Assertions.assertThat(ticket.getPrice().price()).isEqualTo(12L);

    }

    @Test
    public void valid_when_user_just_have_one_type_of_ticket_and_one_ticket() {
        var price = new Price(12L);
        var time = LocalDateTime.now();
        Long eventId;
        Long userId;
        Long ticketId;

//        new BuyTicketRequest(ticketId, userId, price)


//        Ticket ticket = Ticket();

    }
}