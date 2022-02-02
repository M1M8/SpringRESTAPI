package com.sparta.mm.springrestapi.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.sparta.mm.springrestapi.controller.ActorController;
import com.sparta.mm.springrestapi.entities.ActorEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ActorEntityAssembler implements RepresentationModelAssembler<ActorEntity, EntityModel<ActorEntity>> {

    @Override
    public EntityModel<ActorEntity> toModel(ActorEntity entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ActorController.class).findActorById(entity.getActorId())).withSelfRel(),
                linkTo(methodOn(ActorController.class).getActorsByName(entity.getFirstName().toLowerCase(), entity.getActorId())).withRel("actors"));
    }
}
