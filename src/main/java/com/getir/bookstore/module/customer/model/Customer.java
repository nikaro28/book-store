package com.getir.bookstore.module.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tblCustomer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "First Name cannot be blank")
  @ApiModelProperty(required = true)
  private String firstName;

  @NotBlank(message = "Last Name cannot be blank")
  @ApiModelProperty(required = true)
  private String lastName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date birthDate;

  @NotBlank(message = "Email cannot be blank")
  @Column(unique=true)
  @ApiModelProperty(required = true)
  private String email;

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date age) {
    this.birthDate = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
