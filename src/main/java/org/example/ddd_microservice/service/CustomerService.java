package org.example.ddd_microservice.service;

import org.example.ddd_microservice.dto.CustomerLoginDTO;
import org.example.ddd_microservice.dto.CustomerRequestDTO;
import org.example.ddd_microservice.dto.CustomerResponseDTO;
import org.example.ddd_microservice.entity.Customer;
import org.example.ddd_microservice.exception.ResourceNotFoundException;
import org.example.ddd_microservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public CustomerResponseDTO register(CustomerRequestDTO dto){

        Customer customer=new Customer();

        customer.setFullName(dto.getFullName());

        customer.setEmail(dto.getEmail());

        customer.setPassword(
                encoder.encode(dto.getPassword())
        );

        Customer saved=repository.save(customer);

        return new CustomerResponseDTO(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail()
        );

    }

    public CustomerResponseDTO getCustomer(Long id){

        Customer customer=repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Customer not found with id "+id));

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail()
        );

    }

    public CustomerResponseDTO login(CustomerLoginDTO dto){

        Customer customer=repository.findByEmail(dto.getEmail())
                .orElseThrow(()->new RuntimeException(
                        "email or password incorrect"));

        if(!encoder.matches(dto.getPassword(),
                customer.getPassword())){

            throw new RuntimeException(
                    "email or password incorrect");
        }

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail()
        );

    }

}