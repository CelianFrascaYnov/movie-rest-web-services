package com.webservice.apirest.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author_last_name")
    private String authorLastName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_birth_date")
    private String authorBirthDate;

    @ManyToMany(mappedBy = "author")
    private Set<Movie> movie = new HashSet<>();
}
