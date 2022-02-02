package com.sparta.mm.springrestapi.repositories;

import com.sparta.mm.springrestapi.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
