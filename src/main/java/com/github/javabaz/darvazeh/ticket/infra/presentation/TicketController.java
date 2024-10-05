package com.github.javabaz.darvazeh.ticket.infra.presentation;

import com.github.javabaz.darvazeh.ticket.domin.usecase.AddTicketByOrganization;
import com.github.javabaz.darvazeh.ticket.domin.usecase.BuyTicket;
import com.github.javabaz.darvazeh.ticket.infra.presentation.dto.BuyTicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.presentation.dto.TicketRequest;
import com.github.javabaz.darvazeh.ticket.infra.presentation.dto.TicketResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final AddTicketByOrganization addTicketByOrganization;

    private final BuyTicket buyTicket;

    //TODO just admin can access  to change add it
    @PostMapping
    public TicketResponse addTicket(@RequestBody @Valid TicketRequest request) {
        return addTicketByOrganization.addTicket(request);
    }

    @PostMapping("/buy")
    TicketResponse buyTicket(@RequestBody @Valid BuyTicketRequest request) {
        return buyTicket.buyTicket(request);
    }
}
