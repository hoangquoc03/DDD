package org.example.ddd_microservice.controller;

import jakarta.validation.Valid;
import org.example.ddd_microservice.dto.ProductRequestDTO;
import org.example.ddd_microservice.dto.ProductResponseDTO;
import org.example.ddd_microservice.entity.Product;
import org.example.ddd_microservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponseDTO create(
            @Valid @RequestBody ProductRequestDTO dto) {

        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping
    public List<Product> getAll() {

        return service.getAll();
    }
}