package com.github.javabaz.darvazeh.feature.ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    public TicketResponse getTicketResponse(@RequestBody @Valid TicketRequest ticketRequest) {
        return ticketService.add(ticketRequest);
    }
}
