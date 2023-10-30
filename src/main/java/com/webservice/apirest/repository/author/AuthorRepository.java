package com.webservice.apirest.repository.author;

import com.webservice.apirest.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}