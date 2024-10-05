package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.base.BaseEntityRepository;

import java.util.Optional;

public interface UserRepository extends BaseEntityRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String phoneNumber);

}
