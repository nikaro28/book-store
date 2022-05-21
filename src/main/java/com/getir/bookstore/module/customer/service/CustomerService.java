package com.getir.bookstore.module.customer.service;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.customer.model.Customer;
import com.getir.bookstore.module.customer.repository.CustomerRepository;
import com.getir.bookstore.module.order.model.Order;
import com.getir.bookstore.module.order.service.OrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;

    private final OrderService orderService;

    @Autowired
    public CustomerService(CustomerRepository customerRepo, OrderService orderService) {
        this.customerRepo = customerRepo;
        this.orderService = orderService;
    }

    public Result<Long> addCustomer(Customer customer) {
        Customer newCustomer = customerRepo.save(customer);
        return Result.success(newCustomer.getId());
    }

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepo.getReferenceById(customerId);
        return customer;
    }

    public Result<List<Order>> getOrders(Long customerId, Integer pageNo, Integer pageSize) {
        List<Order> customerOrders = orderService.getOrdersByCustomer(customerId, pageNo, pageSize);
        if(CollectionUtils.isEmpty(customerOrders)){
          Result.withError(HttpStatus.NOT_FOUND, "No order found for the customer!");
        }

        return Result.success(customerOrders);
    }

}
