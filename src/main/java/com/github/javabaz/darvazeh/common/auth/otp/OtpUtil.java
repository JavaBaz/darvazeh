package com.github.javabaz.darvazeh.common.auth.otp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OtpUtil {

    private final OtpRepository otpRepository;

    public void sendOtpSms(String phoneNumber, String otp) {
        System.out.println("Sending OTP Sms to: " + phoneNumber + " - OTP : " + otp);
    }


    public String generateOtp() {
        return "1234";
    }

    public boolean isValid()
    {
        return false;
    }
}
