package com.github.javabaz.darvazeh.ticket.infra;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;

public interface TicketJpaRepository extends BaseEntityRepository<TicketEntity, Long> {

    boolean existsByUserId(Long userId);

}
