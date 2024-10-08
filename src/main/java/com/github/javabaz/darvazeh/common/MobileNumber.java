package com.github.javabaz.darvazeh.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
public record MobileNumber(String mobileNumber) {

    private static final String FIRST_CHARACTER = "0";
    private static final String REGION_CODE_IRAN = "+98";
    private static final String REGION_CODE = "IR";

    public MobileNumber {
        if (mobileNumber.startsWith(FIRST_CHARACTER))
            mobileNumber = mobileNumber.replaceFirst(FIRST_CHARACTER, REGION_CODE_IRAN);

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        Optional<Phonenumber.PhoneNumber> number = Optional.empty();

        try {
            number = Optional.ofNullable(phoneNumberUtil.parse(mobileNumber,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name()));
        } catch (NumberParseException exception) {
            log.error("phone number is not valid {}", exception.getMessage());
        }
        number.ifPresent(phoneNumber -> {
            boolean isValid = phoneNumberUtil.isValidNumberForRegion(phoneNumber, REGION_CODE);
            Assert.isTrue(isValid, "phone number is not valid");
        });
    }
}
