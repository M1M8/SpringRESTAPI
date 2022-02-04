package com.sparta.mm.springrestapi;

import com.sparta.mm.springrestapi.controller.ActorController;
import com.sparta.mm.springrestapi.entities.ActorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringRestApiApplicationTests {

    @Autowired
    private ActorController actorController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(actorController);
    }

    @Test
    void doTest() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ActorEntity> response = restTemplate.getForEntity("htttp://localhost:8080/actors/1", ActorEntity.class);
        System.out.println(response.getBody().getFirstName());
        System.out.println(response.getBody().getLastName());

        ActorEntity[] actors = restTemplate.getForObject("htttp://localhost:8080/actors/1", ActorEntity[].class);



    }

}
