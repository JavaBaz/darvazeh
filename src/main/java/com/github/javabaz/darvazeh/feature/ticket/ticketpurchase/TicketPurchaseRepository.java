package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;

import java.util.List;

public interface TicketPurchaseRepository extends BaseEntityRepository<TicketPurchase, Long> {
    List<TicketPurchase> findByUser(UserEntity user);
    List<TicketPurchase> findByTicketType(TicketType ticketType);
}
