package com.getir.bookstore.module.statistics.service;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.order.model.Order;
import com.getir.bookstore.module.order.repository.OrderRepository;
import com.getir.bookstore.module.statistics.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

  private OrderRepository orderRepo;

  @Autowired
  public StatisticsService(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  public Result<List<Statistics>> getCustomerStats(Long customerId, Integer year) {
    final int minYear = 1900;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    if (year == null || year > currentYear) {
      year = currentYear;
    }
    if (year < minYear) {
      year = minYear;
    }

    List<Order> orders = orderRepo.findOrdersByCustomerIdAndYear(customerId, year);
    if (CollectionUtils.isEmpty(orders)) {
      return Result.withError(HttpStatus.NOT_FOUND, "No orders found for the year " + year);
    }

    Calendar cal = Calendar.getInstance();

    Map<Integer, List<Order>> monthlyOrdersMap =
        orders.stream()
            .collect(
                Collectors.groupingBy(
                    o -> {
                      cal.setTime(o.getOrderDate());
                      return cal.get(Calendar.MONTH) + 1;
                    }));

    List<Statistics> statistics =
        monthlyOrdersMap.entrySet().stream()
            .map(
                e ->
                    new Statistics(
                        Month.of(e.getKey()).getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                        e.getValue().size(),
                        e.getValue().stream().map(Order::getQuantity).reduce(0, (a, b) -> a + b),
                        e.getValue().stream()
                            .map(Order::getOrderAmount)
                            .reduce(0f, (a, b) -> a + b)))
            .collect(Collectors.toList());

    return Result.success(statistics);
  }
}
