package com.github.javabaz.darvazeh.common.auth.otp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OtpUtilTest {

    @Test
    void generateRandomOtp() {
        OtpUtil otpUtil = new OtpUtil();
        String otpCode = otpUtil.generateOtp();
        Assertions.assertThat(otpCode.length()).isEqualTo(6);
    }
}