package io.catalyte.demo.movies;

import io.catalyte.demo.movies.moviesEntity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Movies objects.
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    private final MoviesRepository moviesRepository;
    private final TitleUniqueValidator titleValidator;
    private final MoviesValidation validator;

    @Autowired
    public MoviesServiceImpl(MoviesRepository moviesRepository, TitleUniqueValidator titleValidator, MoviesValidation validator) {
        this.moviesRepository = moviesRepository;
        this.titleValidator = titleValidator;
        this.validator = validator;
    }

    /**
     * Retrieves all vendors from the repository.
     *
     * @return a list of all vendors
     */
    public List<Movies> getMovies() {
        return moviesRepository.findAll();
    }

    /**
     * Retrieves a movies by its ID.
     * Throws a ResponseStatusException if the movies is not found.
     * @param id the ID of the movies to retrieve
     * @return the movies with the specified ID
     */
    public Movies getMoviesById(int id) {
        try {
            return moviesRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found.");
        }
    }

    /**
     * Creates a new movie in the repository
     *
     * @param movieToCreate - title of the movie, genre, director, daily rental cost
     * @return the created movie
     */
    @Override
    public Movies createMovie(Movies movieToCreate) {
        List<String> errors = List.of(validator.validateMovie(movieToCreate));
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }

        if (!titleValidator.isTitleUnique(movieToCreate.getTitle(), getMovies()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Title must be unique");
        }

        return moviesRepository.save(movieToCreate);
    }

    /**
     * Edits an existing movies.
     *
     * @param movieToEdit the movies with updated details
     * @param id the ID of the movies to update
     * @return the updated movies
     */
    public Movies editMovie(Movies movieToEdit, int id) {
        if (moviesRepository.findById(id).isPresent()) {
            movieToEdit.setId(id);

            List<String> errors = List.of(validator.validateMovie(movieToEdit));
            if (!errors.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
            }
            return moviesRepository.save(movieToEdit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Movie was not found");
        }
    }

    /**
     * Deletes a movies by its ID
     * Throws a ResponseStatusException if the movies is not found.
     * @param id the ID of the movies to retrieve
     */
    public void deleteMovieById(int id) {
        Optional<Movies> vendorToDelete = moviesRepository.findById(id);

        if (vendorToDelete.isPresent()) {
            moviesRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movies with matching id could not be found.");
    }

}

