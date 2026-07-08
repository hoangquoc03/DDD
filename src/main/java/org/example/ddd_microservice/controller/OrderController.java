package org.example.ddd_microservice.controller;

import org.example.ddd_microservice.dto.*;
import org.example.ddd_microservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDTO create(
            @Valid @RequestBody OrderRequestDTO dto) {

        return service.create(dto);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable Long id) {

        return service.getById(id);
    }

}