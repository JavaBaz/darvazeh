package com.github.javabaz.darvazeh.feature.user.unverified;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, Long> {
    Optional<UnverifiedUser> findByUsernameAndOtpCode(String phoneNumber, String code);

    Optional<UnverifiedUser> findByUsername(String phoneNumber);

    boolean existsByUsername(String phoneNumber);

    void deleteByUsername(String phoneNumber);
}
