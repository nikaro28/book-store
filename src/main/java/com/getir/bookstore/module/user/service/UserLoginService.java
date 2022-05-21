package com.getir.bookstore.module.user.service;

import com.getir.bookstore.config.security.JWTUtil;
import com.getir.bookstore.module.user.model.User;
import com.getir.bookstore.module.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;
  private AuthenticationManager authenticationManager;
  private JWTUtil jwtUtil;

  @Autowired
  public UserLoginService(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      JWTUtil jwtUtil) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  public Optional<String> signin(String username, String password) {
    LOGGER.info("New user attempting to sign in");
    Optional<String> token = Optional.empty();
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        token = Optional.of(jwtUtil.generateAccessToken(username, user.get().getRoles()));
      } catch (AuthenticationException e) {
        LOGGER.info("Log in failed for user {}", username);
      }
    }
    return token;
  }
}
