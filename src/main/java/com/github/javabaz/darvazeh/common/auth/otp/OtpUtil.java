package com.github.javabaz.darvazeh.common.auth.otp;


import com.github.javabaz.darvazeh.feature.user.unverified.UnverifiedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class OtpUtil {

    private static final int BOUND_OTP_CODE = 899_999;
    public static final int SUM_TO_OTP = 100_000;


    public void sendOtpSms(String phoneNumber, String otpCode) {

        //todo api call

    }


    public String generateOtp() {
        var random = new Random();
        int code = random.nextInt(BOUND_OTP_CODE) + SUM_TO_OTP;
        return String.valueOf(code);
    }


}
