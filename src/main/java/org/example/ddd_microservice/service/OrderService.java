package org.example.ddd_microservice.service;

import org.example.ddd_microservice.dto.*;
import org.example.ddd_microservice.entity.Order;
import org.example.ddd_microservice.exception.*;
import org.example.ddd_microservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResponseDTO create(OrderRequestDTO dto) {

        if (dto.getQuantity() <= 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }

        // Giả lập lấy giá từ Product Service
        double productPrice = 100000;

        double total = productPrice * dto.getQuantity();

        Order order = new Order(
                dto.getCustomerId(),
                dto.getProductId(),
                LocalDateTime.now(),
                total
        );

        try {

            Order saved = repository.save(order);

            return new OrderResponseDTO(
                    saved.getId(),
                    saved.getCustomerId(),
                    saved.getProductId(),
                    saved.getOrderDate(),
                    saved.getTotalAmount()
            );

        } catch (Exception e) {

            throw new RuntimeException("Cannot save order");

        }

    }

    public OrderResponseDTO getById(Long id) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        return new OrderResponseDTO(
                order.getId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getOrderDate(),
                order.getTotalAmount()
        );
    }

}