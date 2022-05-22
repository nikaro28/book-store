package com.getir.bookstore.module.order.repository;

import com.getir.bookstore.module.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findOrdersByCustomerIdOrderByOrderDate(Long customerId, Pageable pageable);

  List<Order> findOrdersByOrderDateIsBetween(String startDate, String endDate);

  @Query("select o from Order o where o.customerId=?1 and YEAR(orderDate)=?2")
  List<Order> findOrdersByCustomerIdAndYear(Long customerId, int year);
}
