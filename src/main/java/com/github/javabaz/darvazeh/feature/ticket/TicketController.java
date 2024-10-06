package com.github.javabaz.darvazeh.feature.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @PostMapping("/add")
    public TicketResponse getTicketResponse() {
       return null;
    }
}
