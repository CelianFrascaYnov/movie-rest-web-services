package com.webservice.apirest.controller;

import com.webservice.apirest.entity.Actor;
import com.webservice.apirest.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<Page<Actor>> getAllActors(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Actor> actors = actorService.getAllActors(pageable);

        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        Actor actor = actorService.getActorById(id);
        if (actor != null) {
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor createdActor = actorService.saveActor(actor);
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        Actor existingActor = actorService.getActorById(id);
        if (existingActor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Mise à jour les champs de l'acteur existant avec les nouvelles valeurs.
            existingActor.setActorLastName(updatedActor.getActorLastName());
            existingActor.setActorName(updatedActor.getActorName());

            // Appel du service pour mettre à jour l'acteur.
            Actor updatedActorEntity = actorService.updateActor(existingActor);

            return new ResponseEntity<>(updatedActorEntity, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteActor(@PathVariable Long id) {
        if (actorService.getActorById(id) != null) {
            actorService.deleteActor(id);
            return ResponseEntity.ok(true); // La suppression a réussi
        } else {
            return ResponseEntity.ok(false); // La suppression a échoué car l'ID n'a pas été trouvé
        }
    }
}
