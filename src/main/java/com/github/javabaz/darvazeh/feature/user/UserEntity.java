package com.github.javabaz.darvazeh.feature.user;


import com.github.javabaz.darvazeh.common.base.BaseEntity;
import com.github.javabaz.darvazeh.feature.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity<Long> {

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
