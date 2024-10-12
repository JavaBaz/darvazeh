package com.github.javabaz.darvazeh.feature.ticket.ticketpurchase;

import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketType;
import com.github.javabaz.darvazeh.feature.ticket.tickettype.TicketTypeService;
import com.github.javabaz.darvazeh.feature.user.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketPurchaseServiceImpl extends BaseServiceImpl<TicketPurchase, Long, TicketPurchaseRepository> implements TicketPurchaseService {

    private static final int CODE_LENGTH = 3;
    private static final String CODE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public TicketPurchaseServiceImpl(TicketPurchaseRepository repository) {
        super(repository);
    }

    @Override
    public List<TicketPurchase> getAllByUser(UserEntity user) {
        return repository.findByUser(user);
    }

    @Override
    public List<TicketPurchase> getAllByTicketType(TicketType ticketType) {
        return repository.findByTicketType(ticketType);
    }

    public TicketPurchase purchaseTicket(UserEntity user, TicketType ticketType ) {

        boolean alreadyPurchased = repository.existsByUserAndTicketType_Event(user, ticketType.getEvent());

        if (alreadyPurchased) {
            throw new RuntimeException("User has already purchased a ticket for this event");
        }

        String uniqueCode = generateUniqueCode(ticketType.getEvent());

        TicketPurchase purchase = new TicketPurchase();
        purchase.setUser(user);
        purchase.setTicketType(ticketType);
        purchase.setPurchaseDateTime(LocalDateTime.now());
        purchase.setEntranceCode(uniqueCode);

        return repository.save(purchase);
    }

    private String generateUniqueCode(Event event) {
        String code;
        do {
            code = RandomStringUtils.random(CODE_LENGTH, CODE_CHARACTERS);
        } while (repository.existsByEntranceCodeAndTicketType_Event(code, event));

        return code;
    }
}
