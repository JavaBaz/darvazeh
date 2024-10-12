package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.common.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String phoneNumber);

}
