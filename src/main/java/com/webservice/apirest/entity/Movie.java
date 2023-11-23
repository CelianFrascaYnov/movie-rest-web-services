package com.webservice.apirest.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "film_description")
    private String filmDescription;
    @Column(name = "publication_year")
    private int publicationYear;

    @ManyToMany
    @JoinTable(name = "actor_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actor = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "author_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> author = new HashSet<>();

}
