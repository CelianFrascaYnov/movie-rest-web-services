package com.webservice.apirest.controller;

import com.webservice.apirest.entity.Movie;
import com.webservice.apirest.entity.MovieRequest;
import com.webservice.apirest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "actors", required = false) List<Long> actorIds,
            @RequestParam(value = "authors", required = false) List<Long> authorIds) {

        Pageable pageable = PageRequest.of(page, size);

        // Si des acteurs ou des réalisateurs sont spécifiés, filtrez les films en conséquence.
        Page<Movie> movies;
        if (actorIds != null && !actorIds.isEmpty() && authorIds != null && !authorIds.isEmpty()) {
            // Filtrez les films par acteurs et réalisateurs
            movies = movieService.getMoviesByActorsAndAuthors(actorIds, authorIds, pageable);
        } else if (actorIds != null && !actorIds.isEmpty()) {
            // Filtrez les films par acteurs
            movies = movieService.getMoviesByActors(actorIds, pageable);
        } else if (authorIds != null && !authorIds.isEmpty()) {
            // Filtrez les films par réalisateurs
            movies = movieService.getMoviesByAuthors(authorIds, pageable);
        } else {
            // Aucun filtre, obtenez tous les films.
            movies = movieService.getAllMovies(pageable);
        }

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequest movieRequest) {
        Movie createdMovie = movieService.saveMovie(movieRequest);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie existingMovie = movieService.getMovieById(id);
        if (existingMovie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Mise à jour les champs du film existant avec les nouvelles valeurs.
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setFilmDescription(updatedMovie.getFilmDescription());
            existingMovie.setPublicationYear(updatedMovie.getPublicationYear());

            // Appel du service pour mettre à jour le film.
            Movie updatedMovieEntity = movieService.updateMovie(existingMovie);

            return new ResponseEntity<>(updatedMovieEntity, HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            movieService.deleteMovie(id);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
