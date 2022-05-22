package com.getir.bookstore.module.customer.controller;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.customer.model.Customer;
import com.getir.bookstore.module.customer.service.CustomerService;
import com.getir.bookstore.module.order.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Api("APIs related to resource : CUSTOMER")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully added customer."),
        @ApiResponse(code = 400, message = "The customer info is not complete and valid."),
        @ApiResponse(code = 409, message = "Customer with this info already exists."),
        @ApiResponse(code = 500, message = "Server encountered a problem while processing request.")
      })
  @PostMapping
  public ResponseEntity<Customer> addCustomer(
      @Valid @RequestBody Customer customerDTO, UriComponentsBuilder uriBuilder) {
    Result<Long> result = customerService.addCustomer(customerDTO);
    if (result.isHasError()) {
      return new ResponseEntity(result.getErrorMessage(), result.getStatus());
    }

    URI newCustomerURI =
        uriBuilder.path("/customers/{customerId}").buildAndExpand(result.getObject()).toUri();
    return ResponseEntity.created(newCustomerURI).build();
  }

  @GetMapping("/{customerId}/orders")
  public ResponseEntity getOrders(
      @PathVariable Long customerId,
      @RequestParam(required = false) @DefaultValue(value = "0") Integer pageNo,
      @RequestParam(required = false) @DefaultValue(value = "1") Integer pageSize) {
    Result<List<Order>> result = customerService.getOrders(customerId, pageNo, pageSize);
    if (result.isHasError()) {
      return new ResponseEntity(result.getObject(), result.getStatus());
    }

    if (CollectionUtils.isEmpty(result.getObject())) {
      return new ResponseEntity(result.getErrorMessage(), result.getStatus());
    }

    return ResponseEntity.ok(result.getObject());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return errors;
  }
}
