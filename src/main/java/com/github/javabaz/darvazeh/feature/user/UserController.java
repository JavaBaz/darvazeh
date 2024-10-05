package com.github.javabaz.darvazeh.feature.user;



import com.github.javabaz.darvazeh.feature.user.dto.*;
import com.github.javabaz.darvazeh.feature.user.enums.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PhoneNumberDto phoneNumberDto) {
        userService.registerUser(phoneNumberDto.phoneNumber());
        return ResponseEntity.ok("OTP sent to your phone.");
    }

    @PostMapping("/verify-otp-member")
    public ResponseEntity<String> verifyOtpForMember(@RequestBody OtpVerificationDto otpVerificationDto) {
        userService.verifyOtp(otpVerificationDto.phoneNumber(), otpVerificationDto.otpCode(), UserRole.MEMBER);
        return ResponseEntity.ok("OTP verified. User created.");
    }
    @PostMapping("/verify-otp-organizer")
    public ResponseEntity<String> verifyOtpForTrainer(@RequestBody OtpVerificationDto otpVerificationDto) {
        userService.verifyOtp(otpVerificationDto.phoneNumber(), otpVerificationDto.otpCode(), UserRole.ORGANIZER);
        return ResponseEntity.ok("OTP verified. User created.");
    }



    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestBody PasswordSetupDto passwordSetupDto) {
        userService.setPassword(passwordSetupDto.phoneNumber(), passwordSetupDto.password());
        return ResponseEntity.ok("Password set successfully.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        userService.login(loginDto.phoneNumber(), loginDto.password());
        return ResponseEntity.ok("Login successful.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody PhoneNumberDto phoneNumberDto) {
        userService.sendPasswordResetOtp(phoneNumberDto.phoneNumber());
        return ResponseEntity.ok("Password reset OTP sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        userService.resetPassword(passwordResetDto.phoneNumber(), passwordResetDto.otpCode(), passwordResetDto.newPassword());
        return ResponseEntity.ok("Password reset successfully.");
    }

    @GetMapping("/me")
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserByUsername(username);
    }
}