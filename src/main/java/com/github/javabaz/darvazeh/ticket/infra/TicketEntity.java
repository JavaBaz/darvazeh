package com.github.javabaz.darvazeh.ticket.infra;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.user.MyUser;
import com.github.javabaz.darvazeh.ticket.domin.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class TicketEntity extends BaseEntity<Long> {
    private Long eventId;
    private LocalDateTime dateTime;
    private Long price;
    private Long userId;
    @ManyToOne
    private Event event;
    @OneToOne
    private MyUser user;

    private boolean isBought = false;

    //todo count of tickets for each event
}
