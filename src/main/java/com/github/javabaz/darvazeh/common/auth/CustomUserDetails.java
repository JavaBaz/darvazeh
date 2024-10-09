package com.github.javabaz.darvazeh.common.auth;

import com.github.javabaz.darvazeh.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userObj -> User.builder()
                        .username(userObj.getUsername())
                        .password(userObj.getPassword())
                        .roles(userObj.getUserRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
