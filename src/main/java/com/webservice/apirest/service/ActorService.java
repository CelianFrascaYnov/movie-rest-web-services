package com.webservice.apirest.service;

import com.webservice.apirest.entity.Actor;
import com.webservice.apirest.repository.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Page<Actor> getAllActors(Pageable pageable) {
        return actorRepository.findAll(pageable);
    }

    public Actor getActorById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

}
