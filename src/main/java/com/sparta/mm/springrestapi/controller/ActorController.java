package com.sparta.mm.springrestapi.controller;

import com.sparta.mm.springrestapi.exceptions.ActorNotFoundException;
import com.sparta.mm.springrestapi.entities.ActorEntity;
import com.sparta.mm.springrestapi.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ActorController {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    
//    @GetMapping("/actors")
//    public List<ActorEntity> getAllActors() {
//        return actorRepository.findAll();
//    }

    @GetMapping("/actors")
    public CollectionModel<EntityModel<ActorEntity>> getAllActors() {
        List<EntityModel<ActorEntity>> actors = actorRepository.findAll().stream()
                .map(actor -> EntityModel.of(actor,
                        linkTo(methodOn(ActorController.class).findActorById(actor.getActorId())).withSelfRel(),
                        linkTo(methodOn(ActorController.class).getAllActors()).withRel("actors")))
                .collect(Collectors.toList());

        return CollectionModel.of(actors,
                linkTo(methodOn(ActorController.class).getAllActors()).withSelfRel());
    }

    @GetMapping("/actors/{id}")
    public EntityModel<ActorEntity> findActorById(@PathVariable Integer id) {
        ActorEntity actorEntity = actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
        return EntityModel.of(actorEntity,
                linkTo(methodOn(ActorController.class).findActorById(id)).withSelfRel(),
                linkTo(methodOn(ActorController.class).getAllActors()).withRel("actors"));

    }

    @GetMapping("/actors/search/{search}")
    public CollectionModel<EntityModel<ActorEntity>> findActorByFirstName(@PathVariable String search) {
        List<EntityModel<ActorEntity>> actors = actorRepository.findAll().stream()
                .filter(actor -> actor.getFirstName().contains(search))
                .map(actor -> EntityModel.of(actor,
                        linkTo(methodOn(ActorController.class).findActorByFirstName(search)).withSelfRel(),
                        linkTo(methodOn(ActorController.class).getAllActors()).withRel("actors")))
                .collect(Collectors.toList());

        return CollectionModel.of(actors,
                linkTo(methodOn(ActorController.class).getAllActors()).withSelfRel());
    }

    @PostMapping("/actors")
    public ActorEntity addActor(@RequestBody ActorEntity actor) throws ValidationException {
        if(actor.getActorId() == null && actor.getFirstName() != null && actor.getLastName() != null && actor.getLastUpdate() != null) {
            return actorRepository.save(actor);
        } else {
            throw new ValidationException("Actor cannot be created");
        }
    }

    @PutMapping("/actors")
    public ResponseEntity<ActorEntity> updateActor(@RequestBody ActorEntity actor) {
        if(actorRepository.findById(actor.getActorId()).isPresent()) {
            return new ResponseEntity<>(actorRepository.save(actor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(actor, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable("id") Integer id) {
        actorRepository.deleteById(id);
    }
}
