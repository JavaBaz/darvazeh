package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;
import com.github.javabaz.darvazeh.feature.ticket.TicketEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Event extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "event_category_id")
    private EventCategory eventCategory;
    //todo name is must be unique or not
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String location;
    private long totalCapacity;
    private LocalDate eventDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> ticketEntities;
}
