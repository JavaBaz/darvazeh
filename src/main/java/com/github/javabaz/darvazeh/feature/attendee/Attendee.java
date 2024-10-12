package com.github.javabaz.darvazeh.feature.attendee;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendee extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    @Column(nullable = false)
    private LocalDateTime attendanceDateTime;

    @Column(nullable = false, length = 3)
    private String code;
}
