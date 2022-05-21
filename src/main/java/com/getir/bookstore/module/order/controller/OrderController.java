package com.getir.bookstore.module.order.controller;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.order.model.Order;
import com.getir.bookstore.module.order.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Api("APIs related to resource : ORDER")
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity placeOrder(@RequestBody Order order, UriComponentsBuilder uriBuilder) {
    Result<Long> result = orderService.addOrder(order);
    if(result.isHasError()) {
      return new ResponseEntity(result.getErrorMessage(), result.getStatus());
    }

    URI newOrderUrl =
            uriBuilder.path("/orders/{orderId}").buildAndExpand(result.getObject()).toUri();
    return ResponseEntity.created(newOrderUrl).build();
  }

  @GetMapping("/{orderId}")
  public Order getOrder(@RequestBody Long orderId) {
    return orderService.getOrder(orderId);
  }

  @GetMapping
  public List<Order> getOrdersByDate(@RequestParam String startDate, @RequestParam String endDate) {
    return orderService.getOrdersByDate(startDate, endDate);
  }
}
