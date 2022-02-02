package com.sparta.mm.springrestapi.controller;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer id) {
        super("Could not find actor " + id);
    }
}
