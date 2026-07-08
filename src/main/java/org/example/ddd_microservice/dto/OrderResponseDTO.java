package org.example.ddd_microservice.dto;

import java.time.LocalDateTime;

public class OrderResponseDTO {

    private Long id;
    private Long customerId;
    private Long productId;
    private LocalDateTime orderDate;
    private Double totalAmount;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id,
                            Long customerId,
                            Long productId,
                            LocalDateTime orderDate,
                            Double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}