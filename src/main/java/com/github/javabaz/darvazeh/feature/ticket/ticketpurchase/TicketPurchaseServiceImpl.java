package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPurchaseServiceImpl extends BaseServiceImpl<TicketPurchase, Long, TicketPurchaseRepository> implements TicketPurchaseService {

    public TicketPurchaseServiceImpl(TicketPurchaseRepository repository) {
        super(repository);
    }

    @Override
    public List<TicketPurchase> getByUser(UserEntity user) {
        return repository.findByUser(user);
    }

    @Override
    public List<TicketPurchase> getByTicketType(TicketType ticketType) {
        return repository.findByTicketType(ticketType);
    }
}
