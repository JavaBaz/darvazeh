package com.github.javabaz.darvazeh.common.auth.otp;


import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

    public void sendOtpSms(String phoneNumber, String otp) {
        System.out.println("Sending OTP Sms to: " + phoneNumber + " - OTP : " + otp);
    }


    public String generateOtp(){
        return "1234";
    }
}
