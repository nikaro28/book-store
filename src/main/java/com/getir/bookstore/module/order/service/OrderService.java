package com.getir.bookstore.module.order.service;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.book.model.Book;
import com.getir.bookstore.module.book.repository.BookRepository;
import com.getir.bookstore.module.customer.model.Customer;
import com.getir.bookstore.module.customer.repository.CustomerRepository;
import com.getir.bookstore.module.order.model.Order;
import com.getir.bookstore.module.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
  private final OrderRepository orderRepo;
  private final BookRepository bookRepo;
  private final CustomerRepository customerRepo;

  @Autowired
  public OrderService(
      OrderRepository orderRepo, BookRepository bookRepo, CustomerRepository customerRepo) {
    this.orderRepo = orderRepo;
    this.bookRepo = bookRepo;
    this.customerRepo = customerRepo;
  }

  public Result<Long> addOrder(Order order) {
    Book book = bookRepo.findById(order.getBookId()).orElse(null);
    if (book == null) {
      return Result.withError(HttpStatus.BAD_REQUEST, "Please use valid book Id!");
    }

    if (book.getStock() < order.getQuantity()) {
      return Result.withError(
          HttpStatus.NOT_FOUND,
          "Current available stock for this book is : " + book.getStock() + "!");
    }

    Customer customer = customerRepo.findById(order.getCustomerId()).orElse(null);
    if (customer == null) {
      return Result.withError(HttpStatus.BAD_REQUEST, "Please use valid customer Id!");
    }

    Order addedOrder = updateStockAndAddOrder(book, order, order.getQuantity());
    if (addedOrder == null) {
      return Result.withError(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again!");
    }

    return Result.success(addedOrder.getId());
  }

  @Transactional
  private Order updateStockAndAddOrder(Book book, Order order, int bookCount) {
    if (book.getStock() < bookCount) {
      return null;
    }
    book.setStock(book.getStock() - bookCount);
    bookRepo.save(book);

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date orderDate = new Date();
    formatter.format(orderDate);
    order.setOrderDate(orderDate);
    order.setOrderAmount(book.getPrice() * bookCount);

    return orderRepo.save(order);
  }

  public Order getOrder(Long orderId) {
    return orderRepo.findById(orderId).orElse(null);
  }

  public List<Order> getOrdersByDate(String startDate, String endDate) {
    return orderRepo.findOrdersByOrderDateIsBetween(startDate, endDate);
  }

  public List<Order> getOrdersByCustomer(Long customerId, Integer pageNo, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Order> pagedResult =
        orderRepo.findOrdersByCustomerIdOrderByOrderDate(customerId, pageable);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return Collections.emptyList();
    }
  }
}
