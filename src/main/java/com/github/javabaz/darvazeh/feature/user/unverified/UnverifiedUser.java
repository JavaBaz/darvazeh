package com.github.javabaz.darvazeh.feature.user.unverified;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UnverifiedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String otpCode;

    @Column(nullable = false)
    private LocalDateTime otpGeneratedAt; // Timestamp when the OTP was generated

    public UnverifiedUser(String username, String otpCode, LocalDateTime otpGeneratedAt) {
        this.username = username;
        this.otpCode = otpCode;
        this.otpGeneratedAt = otpGeneratedAt;
    }

    public UnverifiedUser() {

    }
}