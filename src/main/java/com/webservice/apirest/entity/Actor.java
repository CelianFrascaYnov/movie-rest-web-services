package com.webservice.apirest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "actor_last_name")
    private String actorLastName;
    @Column(name = "actor_name")
    private String actorName;
    @Column(name = "actor_birth_date")
    private String actorBirthDate;

    @ManyToMany(mappedBy = "actor")
    private Set<Movie> movie = new HashSet<>();

}
