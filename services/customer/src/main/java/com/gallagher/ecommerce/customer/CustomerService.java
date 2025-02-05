package com.gallagher.ecommerce.customer;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public String createCustomer(@Valid CustomerRequest request) {

        return null;
    }
}
