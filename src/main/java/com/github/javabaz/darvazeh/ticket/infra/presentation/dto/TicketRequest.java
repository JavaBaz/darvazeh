package com.github.javabaz.darvazeh.ticket.infra.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketRequest {
    @NotNull
    private Long eventId;
    private LocalDateTime startTime;
    private Long price;

}
