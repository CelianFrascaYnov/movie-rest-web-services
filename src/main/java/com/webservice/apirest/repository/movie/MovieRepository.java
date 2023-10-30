package com.webservice.apirest.repository.movie;

import com.webservice.apirest.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m " +
            "INNER JOIN m.actor a " +
            "INNER JOIN m.author au " +
            "WHERE a.id IN :actorIds AND au.id IN :authorIds " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT a.id) = :actorCount AND COUNT(DISTINCT au.id) = :authorCount")
    Page<Movie> getMoviesByActorsAndAuthors(@Param("actorIds") List<Long> actorIds, @Param("authorIds") List<Long> authorIds, @Param("actorCount") Long actorCount, @Param("authorCount") Long authorCount, Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "INNER JOIN m.actor a " +
            "WHERE a.id IN :actorIds " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT a.id) = :actorCount")
    Page<Movie> getMoviesByActors(@Param("actorIds") List<Long> actorIds, @Param("actorCount") Long actorCount, Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "INNER JOIN m.author au " +
            "WHERE au.id IN :authorIds " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT au.id) = :authorCount")
    Page<Movie> getMoviesByAuthors(@Param("authorIds") List<Long> authorIds, @Param("authorCount") Long authorCount, Pageable pageable);
}
