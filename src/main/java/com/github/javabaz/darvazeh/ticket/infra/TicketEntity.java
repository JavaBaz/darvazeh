package com.github.javabaz.darvazeh.ticket.infra;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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


}
