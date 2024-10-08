package com.github.javabaz.darvazeh.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MobileNumberShould {


    @ParameterizedTest
    @ValueSource(strings = {"0549302223121", "0234545549", "056345549", "0565463254349", "0523349"})
    void giveMeExceptionWhenIsNotValid(String phoneNumber) {


        assertThatThrownBy(() -> new MobileNumber(phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("phone number is not valid");
    }

}