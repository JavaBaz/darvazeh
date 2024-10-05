package com.github.javabaz.darvazeh.ticket.infra.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuyTicketRequest extends TicketRequest {
    @NotNull
    private Long ticketId;

}
