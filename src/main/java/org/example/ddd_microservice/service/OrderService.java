package org.example.ddd_microservice.service;

import org.example.ddd_microservice.dto.*;
import org.example.ddd_microservice.entity.Order;
import org.example.ddd_microservice.exception.*;
import org.example.ddd_microservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository repository;

    private final RestTemplate restTemplate;

    public OrderService(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
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
    public ProductDTO getProduct(Long id){

        String url =
                "http://localhost:8082/api/v1/products/" + id;

        try{

            return restTemplate.getForObject(

                    url,

                    ProductDTO.class

            );

        }

        catch(Exception e){

            throw new RuntimeException(

                    "Dịch vụ sản phẩm hiện không khả dụng, vui lòng thử lại sau"

            );

        }

    }


}