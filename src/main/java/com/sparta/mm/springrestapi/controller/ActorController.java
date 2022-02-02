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
import java.util.Locale;
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

    @GetMapping("/actors")
    @ResponseBody
    public CollectionModel<EntityModel<ActorEntity>> getActorsByName(@RequestParam(required = false, name = "name") String name, Integer id) {
        if (name == null) {
            List<EntityModel<ActorEntity>> actors = actorRepository.findAll().stream()
                    .map(actor -> EntityModel.of(actor,
                            linkTo(methodOn(ActorController.class).findActorById(actor.getActorId())).withRel("actors")))
                    .collect(Collectors.toList());

            return CollectionModel.of(actors,
                    linkTo(methodOn(ActorController.class).findActorById(id)).withRel("actors"));
        } else {
            List<EntityModel<ActorEntity>> actors = actorRepository.findAll().stream()
                    .filter(actor -> actor.getFirstName().contains(name.toUpperCase()))
                    .map(actor -> EntityModel.of(actor,
                            linkTo(methodOn(ActorController.class).getActorsByName(name, actor.getActorId())).withSelfRel(),
                            linkTo(methodOn(ActorController.class).findActorById(actor.getActorId())).withRel("actors")))
                    .collect(Collectors.toList());

            return CollectionModel.of(actors,
                    linkTo(methodOn(ActorController.class).getActorsByName(name, id)).withSelfRel());
        }
    }

    @GetMapping("/actors/{id}")
    public EntityModel<ActorEntity> findActorById(@PathVariable Integer id) {
        ActorEntity actorEntity = actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
        return EntityModel.of(actorEntity,
                linkTo(methodOn(ActorController.class).findActorById(id)).withSelfRel(),
                linkTo(methodOn(ActorController.class).getActorsByName(actorEntity.getFirstName(), id)).withRel("actors"));

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
