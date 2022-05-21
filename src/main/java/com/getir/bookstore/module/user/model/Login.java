package com.getir.bookstore.module.user.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class Login {

  @NotBlank(message = "Username cannot be blank")
  @ApiModelProperty(required = true)
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @ApiModelProperty(required = true)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
