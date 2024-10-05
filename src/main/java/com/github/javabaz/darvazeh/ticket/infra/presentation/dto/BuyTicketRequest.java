package com.github.javabaz.darvazeh.ticket.infra.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuyTicketRequest extends TicketRequest {
    private Long ticketId;

}
