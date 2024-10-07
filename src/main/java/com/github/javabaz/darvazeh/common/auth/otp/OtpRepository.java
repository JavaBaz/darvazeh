package com.github.javabaz.darvazeh.common.auth.otp;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends BaseEntityRepository<OTP, Integer> {

    Optional<OTP> findByUserIdAndCode(Long userId, String code);
}
