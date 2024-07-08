package io.catalyte.demo.movies;

import io.catalyte.demo.movies.moviesEntity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing Movies entities in the database
 * Extends JpaRepository to provide CRUD operations
 */

@Repository
public interface MoviesRepository extends
        JpaRepository<Movies, Integer> {
    List<Movies> findByTitleIgnoreCase(String title);
}
