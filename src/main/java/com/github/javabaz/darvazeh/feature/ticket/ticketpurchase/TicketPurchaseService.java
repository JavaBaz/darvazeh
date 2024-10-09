package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseService;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;

import java.util.List;

public interface TicketPurchaseService extends BaseService<TicketPurchase, Long> {

    List<TicketPurchase> getByUser(UserEntity user);
    List<TicketPurchase> getByTicketType(TicketType ticketType);
}
