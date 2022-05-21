package com.getir.bookstore.module.order.repository;

import com.getir.bookstore.module.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  /* public List<Order> findByCustomer(Long customerId, int page, int size) {
    return findAll(PageRequest.of(page, size, Sort.by("OrderDate"))).toList();
  }*/

  Page<Order> findOrdersByCustomerIdOrderByOrderDate(Long customerId, Pageable pageable);

  List<Order> findOrdersByOrderDateIsBetween(String startDate, String endDate);
}
