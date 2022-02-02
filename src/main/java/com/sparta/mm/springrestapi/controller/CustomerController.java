package com.sparta.mm.springrestapi.controller;

import com.sparta.mm.springrestapi.assembler.CustomerEntityAssembler;
import com.sparta.mm.springrestapi.entities.CustomerEntity;
import com.sparta.mm.springrestapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerEntityAssembler customerEntityAssembler;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerEntityAssembler customerEntityAssembler) {
        this.customerRepository = customerRepository;
        this.customerEntityAssembler = customerEntityAssembler;
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<CustomerEntity>> getAllCustomers() {
        List<EntityModel<CustomerEntity>> customers = customerRepository.findAll()
                .stream()
                .map(customerEntityAssembler::toModel)
                .toList();

        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
    }

    @GetMapping("customers/{id}")
    public EntityModel<CustomerEntity> getCustomerById(@PathVariable Integer id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerEntityAssembler.toModel(customer);
    }

}