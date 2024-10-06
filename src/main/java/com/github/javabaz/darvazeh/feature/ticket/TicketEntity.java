package com.github.javabaz.darvazeh.feature.ticket;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TicketEntity extends BaseEntity<Long> {
    private long price;
    @Column(nullable = false)
    private LocalDateTime enableDateFrom;
    private LocalDateTime enableDateTo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;
    private int countOfTicket;
}
