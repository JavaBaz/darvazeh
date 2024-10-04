package com.github.javabaz.darvazeh.feature.event;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.event.enums.EventType;
import com.github.javabaz.darvazeh.feature.event.eventcategory.EventCategory;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Event extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "event_category_id")
    private EventCategory eventCategory;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String location;
    private long totalCapacity;
    private LocalDate eventDate;
}
