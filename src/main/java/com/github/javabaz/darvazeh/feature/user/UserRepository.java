package com.github.javabaz.darvazeh.feature.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String phoneNumber);

}
