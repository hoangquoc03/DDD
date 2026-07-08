package org.example.ddd_microservice.controller;

import jakarta.validation.Valid;
import org.example.ddd_microservice.dto.CustomerLoginDTO;
import org.example.ddd_microservice.dto.CustomerRequestDTO;
import org.example.ddd_microservice.dto.CustomerResponseDTO;
import org.example.ddd_microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
    public CustomerResponseDTO register(
            @Valid @RequestBody CustomerRequestDTO dto){

        return service.register(dto);

    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(
            @PathVariable Long id){

        return service.getCustomer(id);

    }

    @PutMapping("/login")
    public CustomerResponseDTO login(
            @RequestBody CustomerLoginDTO dto){

        return service.login(dto);

    }

}