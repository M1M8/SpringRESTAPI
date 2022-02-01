package com.sparta.mm.springrestapi;

import com.sparta.mm.springrestapi.entities.ActorEntity;
import com.sparta.mm.springrestapi.repositories.ActorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RESTIntTests {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void doTest() {
        List<ActorEntity> all = actorRepository.findAll();
        Assertions.assertEquals(201, all.size());
    }

}
