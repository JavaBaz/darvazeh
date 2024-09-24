package com.github.javabaz.darvazeh.feature.user.dto;

public record PasswordResetDto(String phoneNumber, String otpCode, String newPassword) {
}
