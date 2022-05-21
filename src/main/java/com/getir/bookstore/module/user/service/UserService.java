package com.getir.bookstore.module.user.service;

import com.getir.bookstore.config.security.JWTUtil;
import com.getir.bookstore.module.user.model.User;
import com.getir.bookstore.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepo;

  @Autowired
  JWTUtil jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(s).orElseThrow(() ->
        new UsernameNotFoundException(String.format("User with name %s does not exist", s)));

    return withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(user.getRoles())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }

  public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
    if (jwtUtil.validate(jwtToken)) {
      return Optional.of(
          withUsername(jwtUtil.getUsername(jwtToken))
              .authorities(jwtUtil.getRoles(jwtToken))
              .password("") //token does not have password but field may not be empty
              .accountExpired(false)
              .accountLocked(false)
              .credentialsExpired(false)
              .disabled(false)
              .build());
    }
    return Optional.empty();
  }
}
