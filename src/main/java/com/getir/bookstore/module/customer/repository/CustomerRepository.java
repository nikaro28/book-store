package com.getir.bookstore.module.customer.repository;

import com.getir.bookstore.module.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findCustomerByEmail(String email);

  @Override
  Customer getReferenceById(Long customerId);

}
