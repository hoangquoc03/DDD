package org.example.ddd_microservice.repository;

import org.example.ddd_microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}