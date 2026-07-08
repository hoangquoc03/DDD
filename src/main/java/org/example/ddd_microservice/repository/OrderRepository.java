package org.example.ddd_microservice.repository;


import org.example.ddd_microservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}