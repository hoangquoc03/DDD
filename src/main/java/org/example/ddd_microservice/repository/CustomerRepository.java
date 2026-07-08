package org.example.ddd_microservice.repository;

import org.example.ddd_microservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

}