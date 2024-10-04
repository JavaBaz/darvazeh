package com.github.javabaz.darvazeh.feature.event.eventcategory;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EventCategory extends BaseEntity<Long>{

    @NotNull
    private String categoryName;
    private String description;

}
