package com.getir.bookstore.module.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="tblOrder")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Customer Id is required.")
  @ApiModelProperty(required = true)
  private Long customerId;

  @NotNull(message = "Book Id is required.")
  @ApiModelProperty(required = true)
  private Long bookId;

  @Min(value = 1, message = "Minimum 1 quantity must be ordered.")
  @ApiModelProperty(required = true)
  private int quantity;

  @ApiModelProperty(required = true)
  @Enumerated(EnumType.STRING)
  private PaymentMode paymentMode;

  @ApiModelProperty(required = true)
  @NotNull(message = "Order amount is required.")
  private Float orderAmount;

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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public PaymentMode getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(PaymentMode paymentMode) {
    this.paymentMode = paymentMode;
  }

  public Float getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(Float orderAmount) {
    this.orderAmount = orderAmount;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }
}
