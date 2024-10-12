package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseRepository;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;

import java.util.List;

public interface TicketPurchaseRepository extends BaseRepository<TicketPurchase, Long> {
    List<TicketPurchase> findByUser(UserEntity user);
    List<TicketPurchase> findByTicketType(TicketType ticketType);
    boolean existsByEntranceCodeAndTicketType_Event(String code, Event event);
    boolean existsByUserAndTicketType_Event(UserEntity user, Event event);
}
