package com.github.javabaz.darvazeh.common.auth.otp;

import com.github.javabaz.darvazeh.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OTP extends BaseEntity<Integer> {

    @Column(name = "user_id_fk", nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDateTime validTime;

}
