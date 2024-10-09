package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.auth.otp.OtpUtil;
import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceShould {
   
    private  UserRepository userRepository;
    private  UnverifiedUserRepository unverifiedUserRepository; // This part must be failed in ArchUnit test!
    private  OtpUtil otpUtil;
    private  PasswordEncoder passwordEncoder;
}