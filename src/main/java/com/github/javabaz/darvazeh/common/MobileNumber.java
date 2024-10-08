package com.github.javabaz.darvazeh.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
public record MobileNumber(String mobileNumber) {

    public MobileNumber {
        if (mobileNumber.startsWith("0"))
            mobileNumber = mobileNumber.replaceFirst("0", "+98");

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        Optional<Phonenumber.PhoneNumber> number = Optional.empty();

        try {
            number = Optional.ofNullable(phoneNumberUtil.parse(mobileNumber,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name()));
        } catch (NumberParseException exception) {
            log.error("phone number is not valid {}", exception.getMessage());
        }
        number.ifPresent(phoneNumber -> {
            boolean isValid = phoneNumberUtil.isValidNumberForRegion(phoneNumber, "IR");
            Assert.isTrue(isValid, "phone number is not valid");
        });
    }
}
