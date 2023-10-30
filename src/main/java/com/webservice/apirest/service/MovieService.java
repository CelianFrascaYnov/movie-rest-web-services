package com.webservice.apirest.service;

import com.webservice.apirest.entity.Actor;
import com.webservice.apirest.entity.Author;
import com.webservice.apirest.entity.Movie;
import com.webservice.apirest.entity.MovieRequest;
import com.webservice.apirest.repository.actor.ActorRepository;
import com.webservice.apirest.repository.author.AuthorRepository;
import com.webservice.apirest.repository.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final ActorRepository actorRepository;

    private final AuthorRepository authorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, AuthorRepository authorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.authorRepository = authorRepository;
    }

    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie saveMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setFilmDescription(movieRequest.getFilmDescription());
        movie.setPublicationYear(movieRequest.getPublicationYear());

        // Chargement des acteurs et des auteurs en fonction de leurs identifiants
        List<Actor> actors = actorRepository.findAllById(movieRequest.getActorIds());
        List<Author> authors = authorRepository.findAllById(movieRequest.getAuthorIds());

        // Association des acteurs et des auteurs au film
        movie.setActor(new HashSet<>(actors));
        movie.setAuthor(new HashSet<>(authors));

        // Enregistrement du film en base de données
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Page<Movie> getMoviesByActorsAndAuthors(List<Long> actorIds, List<Long> authorIds, Pageable pageable) {
        Long actorCount = (long) actorIds.size();
        Long authorCount = (long) authorIds.size();
        return movieRepository.getMoviesByActorsAndAuthors(actorIds, authorIds, actorCount, authorCount, pageable);
    }

    public Page<Movie> getMoviesByActors(List<Long> actorIds, Pageable pageable) {
        Long actorCount = (long) actorIds.size();
        return movieRepository.getMoviesByActors(actorIds, actorCount, pageable);
    }

    public Page<Movie> getMoviesByAuthors(List<Long> authorIds, Pageable pageable) {
        Long authorCount = (long) authorIds.size();
        return movieRepository.getMoviesByAuthors(authorIds, authorCount, pageable);
    }
}
