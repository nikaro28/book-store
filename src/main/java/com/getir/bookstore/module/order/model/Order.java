package com.getir.bookstore.module.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="tblOrder")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Customer Id cannot be blank")
  @ApiModelProperty(required = true)
  private Long customerId;

  @NotNull(message = "Book Id cannot be blank")
  @ApiModelProperty(required = true)
  private Long bookId;

  @ApiModelProperty(required = true)
  @Enumerated(EnumType.STRING)
  private PaymentMode paymentMode;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @NotNull
  @ApiModelProperty(required = true)
  private Date orderDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public PaymentMode getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(PaymentMode paymentMode) {
    this.paymentMode = paymentMode;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }
}
