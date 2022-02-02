package com.sparta.mm.springrestapi.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.sparta.mm.springrestapi.controller.CustomerController;
import com.sparta.mm.springrestapi.entities.CustomerEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityAssembler implements RepresentationModelAssembler<CustomerEntity, EntityModel<CustomerEntity>> {

    @Override
    public EntityModel<CustomerEntity> toModel(CustomerEntity entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
    }
}
