package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TicketPurchase extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @Column(nullable = false)
    private LocalDateTime purchaseDateTime;
}
