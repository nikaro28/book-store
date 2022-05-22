package com.getir.bookstore.module.user.controller;

import com.getir.bookstore.module.user.model.Login;
import com.getir.bookstore.module.user.service.UserLoginService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@Api("Authentication")
@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserLoginService userService;

  @Autowired
  public UserController(UserLoginService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public String login(@RequestBody Login userLogin) {
    return userService.signin(userLogin.getUsername(), userLogin.getPassword()).orElseThrow(()->
        new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
  }

}
