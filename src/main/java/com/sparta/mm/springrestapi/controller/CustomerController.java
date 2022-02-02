package com.sparta.mm.springrestapi.controller;

import com.sparta.mm.springrestapi.entities.CustomerEntity;
import com.sparta.mm.springrestapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

}
