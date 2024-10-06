package com.github.javabaz.darvazeh.feature.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateTicketTypeRequest {
    @NotNull
    private long eventId;
    private long price;
    @NotNull
    private LocalDateTime enabledFrom;
    private LocalDateTime enabledTo;
    private int quantity;
}
