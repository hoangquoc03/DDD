package org.example.ddd_microservice.service;



import org.example.ddd_microservice.dto.ProductRequestDTO;
import org.example.ddd_microservice.dto.ProductResponseDTO;
import org.example.ddd_microservice.entity.Product;
import org.example.ddd_microservice.exception.ResourceNotFoundException;
import org.example.ddd_microservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());

        Product saved = repository.save(product);

        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStockQuantity()
        );
    }

    public ProductResponseDTO getById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}