package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.auth.otp.OtpUtil;
import com.github.javabaz.darvazeh.common.base.BaseServiceImpl;
import com.github.javabaz.darvazeh.feature.user.enums.UserRole;

import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUser;
import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService extends BaseServiceImpl implements UserDetailsService {

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
        Assert.isTrue(userRepository.existsByUsername(phoneNumber), "Phone number is already registered.");

        Assert.isTrue(unverifiedUserRepository.existsByUsername(phoneNumber),
                "Phone number is already pending verification.");


        String otp = otpUtil.generateOtp();
        UnverifiedUser unverifiedUser = new UnverifiedUser(phoneNumber, otp, LocalDateTime.now());
        unverifiedUserRepository.save(unverifiedUser);

        otpUtil.sendOtpSms(phoneNumber, otp);
    }


    public void verifyOtp(String phoneNumber, String otp, UserRole role) {
        UnverifiedUser unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("Phone number is not pending verification."));

        Optional.of(unverifiedUser).filter(user -> user.getOtpCode().equals(otp))
                .orElseThrow(() -> new IllegalStateException("Invalid OTP."));

        var newUser = new MyUser();
        newUser.setUsername(phoneNumber);
        newUser.setUserRole(role);
        userRepository.save(newUser);

        unverifiedUserRepository.deleteByUsername(phoneNumber);
    }

    public MyUser login(String phoneNumber, String password) {
        MyUser user = userRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if (user.getPassword() == null) {
            throw new IllegalStateException("User has not set a password yet. Please set your password.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid password.");
        }

        return user;
    }

    public void sendPasswordResetOtp(String phoneNumber) {
        userRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        String otp = otpUtil.generateOtp();
        otpUtil.sendOtpSms(phoneNumber, otp);

        var resetRequest = new UnverifiedUser(phoneNumber, otp, LocalDateTime.now());
        unverifiedUserRepository.save(resetRequest);
    }

    public void resetPassword(String phoneNumber, String otp, String newPassword) {
        var unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("No OTP request found for this phone number."));


        if (!unverifiedUser.getOtpCode().equals(otp)) {
            throw new IllegalStateException("Invalid OTP.");
        }


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
}
