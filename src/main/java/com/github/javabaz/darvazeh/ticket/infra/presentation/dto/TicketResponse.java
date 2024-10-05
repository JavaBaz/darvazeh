package com.github.javabaz.darvazeh.ticket.infra.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder

public class TicketResponse {
    private Long Id;
    private Long eventId;
    private Long price;
    private Long userId;
    private LocalDateTime date;
}
