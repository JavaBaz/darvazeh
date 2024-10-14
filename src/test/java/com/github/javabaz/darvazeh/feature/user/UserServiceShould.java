package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.auth.otp.OtpUtil;
import com.github.javabaz.darvazeh.feature.user.enums.UserRole;
import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceShould {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UnverifiedUserRepository unverifiedUserRepository;

    @MockBean
    private OtpUtil otpUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @AfterEach
    void tearDown() {
        unverifiedUserRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void registerUser_Success() {

        String phoneNumber = "1234567890";
        Mockito.doNothing().when(otpUtil).sendOtpSms(Mockito.eq(phoneNumber), Mockito.anyString());

        userService.registerUser(phoneNumber);

        var unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber);
        assertThat(unverifiedUser).isPresent();
        var unverifiedUserAfterGet = unverifiedUser.get();
        assertThat(unverifiedUserAfterGet.getOtpCode()).isEqualTo("123456");
    }

    @Test
    void verifyOtp_Success() {

        String phoneNumber = "1234567890";
        userService.registerUser(phoneNumber);

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

        userService.verifyOtp(phoneNumber, "123456", UserRole.ADMIN);

        var user = userRepository.findByUsername(phoneNumber);
        assertThat(user).isPresent();
        assertThat(user.get().getUserRole()).isEqualTo(UserRole.ADMIN);

        var unverifiedUser = unverifiedUserRepository.findByUsername(phoneNumber);
        assertThat(unverifiedUser).isNotPresent();
    }

    @Test
    void verifyOtp_InvalidOtp() {

        String phoneNumber = "1234567890";
        userService.registerUser(phoneNumber);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> userService.verifyOtp(phoneNumber, "wrongOtp", UserRole.ADMIN));

        assertThat(exception.getMessage()).isEqualTo("Invalid OTP.");
    }
}