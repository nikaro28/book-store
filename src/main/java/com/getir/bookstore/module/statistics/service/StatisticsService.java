package com.getir.bookstore.module.statistics.service;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.customer.service.CustomerService;
import com.getir.bookstore.module.order.model.Order;
import com.getir.bookstore.module.order.service.OrderService;
import com.getir.bookstore.module.statistics.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private CustomerService customerService;

    private OrderService orderService;


    @Autowired
    public StatisticsService(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    public Result<Statistics> getCustomerStats(Long customerId, String fromDate, String toDate) {
        return null;
    }

}
