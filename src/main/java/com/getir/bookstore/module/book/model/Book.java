package com.getir.bookstore.module.book.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="tblBook")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title cannot be blank")
  @ApiModelProperty(required = true)
  private String title;

  @NotBlank(message = "Author cannot be blank")
  @ApiModelProperty(required = true)
  private String author;

  private String publisher;
  private Integer pageCount;

  @NotNull
  @Positive(message = "Price must be positive")
  @ApiModelProperty(required = true)
  private Float price;

  @Min(1)
  @Max(Integer.MAX_VALUE)
  private Integer stock;

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}
