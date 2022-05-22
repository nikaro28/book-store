package com.getir.bookstore.module.statistics.model;

import javax.validation.constraints.NotNull;

public class Statistics {

  private String month;

  private int orderCount;

  private int bookCount;

  private float purchasedAmount;

  public Statistics(@NotNull String month, int orderCount, int bookCount, float purchasedAmount) {
    this.month = month;
    this.orderCount = orderCount;
    this.bookCount = bookCount;
    this.purchasedAmount = purchasedAmount;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public int getOrderCount() {
    return orderCount;
  }

  public void setOrderCount(int orderCount) {
    this.orderCount = orderCount;
  }

  public int getBookCount() {
    return bookCount;
  }

  public void setBookCount(int bookCount) {
    this.bookCount = bookCount;
  }

  public float getPurchasedAmount() {
    return purchasedAmount;
  }

  public void setPurchasedAmount(float purchasedAmount) {
    this.purchasedAmount = purchasedAmount;
  }
}
