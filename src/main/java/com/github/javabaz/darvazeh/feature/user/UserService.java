package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.MobileNumber;
import com.github.javabaz.darvazeh.common.Validation;
import com.github.javabaz.darvazeh.common.auth.otp.OtpUtil;
import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.user.enums.UserRole;

import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUser;
import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.github.javabaz.darvazeh.common.Validation.*;
import static java.util.Objects.*;

@Slf4j
@Service
public class UserService extends BaseServiceImpl<UserEntity, Long, UserRepository> implements UserDetailsService {

    private final UserRepository userRepository;
    private final UnverifiedUserRepository unverifiedUserRepository; // This part must be failed in ArchUnit test!
    private final OtpUtil otpUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UnverifiedUserRepository unverifiedUserRepository, OtpUtil otpUtil, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.unverifiedUserRepository = unverifiedUserRepository;
        this.otpUtil = otpUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userObj -> User.builder()
                        .username(userObj.getUsername())
                        .password(userObj.getPassword())
                        .roles(userObj.getUserRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


    public void registerUser(String phoneNumber) {
        var mobile = new MobileNumber(phoneNumber);
        Assert.isTrue(userRepository.existsByUsername(mobile.mobileNumber()), "Phone number is already registered.");

        Assert.isTrue(unverifiedUserRepository.existsByUsername(mobile.mobileNumber()),
                "Phone number is already pending verification.");


        String otp = otpUtil.generateOtp();
        UnverifiedUser unverifiedUser = new UnverifiedUser(mobile.mobileNumber(), otp, LocalDateTime.now());
        unverifiedUserRepository.save(unverifiedUser);

        otpUtil.sendOtpSms(mobile.mobileNumber(), otp);
    }


    public void verifyOtp(String phoneNumber, String otp, UserRole role) {
        UnverifiedUser unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("Phone number is not pending verification."));

        Optional.of(unverifiedUser).filter(user -> user.getOtpCode().equals(otp))
                .orElseThrow(() -> new IllegalStateException("Invalid OTP."));

        var newUser = new UserEntity();
        newUser.setUsername(phoneNumber);
        newUser.setUserRole(role);
        userRepository.save(newUser);

        unverifiedUserRepository.deleteByUsername(phoneNumber);
    }

    public UserEntity login(String phoneNumber, String password) {
        UserEntity user = userRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        isTrue(nonNull(user.getPassword()), "User has not set a password yet. Please set your password.");

        isTrue(passwordEncoder.matches(password, user.getPassword()), "Invalid password.");


        return user;
    }

    public void sendPasswordResetOtp(String phoneNumber) {
        userRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        String otpCode = otpUtil.generateOtp();
        otpUtil.sendOtpSms(phoneNumber, otpCode);

        var resetRequest = new UnverifiedUser(phoneNumber, otpCode, LocalDateTime.now());
        unverifiedUserRepository.save(resetRequest);
    }

    public void resetPassword(String phoneNumber, String otp, String newPassword) {
        var unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("No OTP request found for this phone number."));


        isTrue(unverifiedUser.getOtpCode().equals(otp),"Invalid OTP.");


        userRepository.findByUsername(phoneNumber).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        });


        unverifiedUserRepository.deleteByUsername(phoneNumber);
    }

    public void setPassword(String phoneNumber, String password) {
        userRepository.findByUsername(phoneNumber).ifPresentOrElse(user -> {
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                },
                () -> {
                    throw new IllegalStateException("User not found.");
                });

    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }


}
