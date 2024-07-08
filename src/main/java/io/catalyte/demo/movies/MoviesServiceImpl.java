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

    @Autowired
    public MoviesServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
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
        return moviesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found."));
    }

    /**
     * Creates a new movie in the repository
     *
     * @param movieToCreate - title of the movie, genre, director, daily rental cost
     * @return the created movie
     */
    @Override
    public Movies createMovie(Movies movieToCreate) {
        MoviesValidation validator = new MoviesValidation();
        TitleUniqueValidator titleValidator = new TitleUniqueValidator();

        List<String> errors = List.of(validator.validateMovie(movieToCreate));
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }

        String titleValidationMessage = titleValidator.isTitleUnique(movieToCreate.getTitle(), getMovies());
        if (!titleValidationMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, titleValidationMessage);
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
        MoviesValidation validator = new MoviesValidation();

        if (moviesRepository.findById(id).isPresent()) {
            movieToEdit.setId(id);

            List<String> errors = List.of(validator.validateMovie(movieToEdit));
            if (!errors.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
            }
            return moviesRepository.save(movieToEdit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie was not found");
        }
    }

    /**
     * Deletes a movies by its ID
     * Throws a ResponseStatusException if the movies is not found.
     * @param id the ID of the movies to retrieve
     */
    public void deleteMovieById(int id) {
        if (moviesRepository.findById(id).isPresent()) {
            moviesRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with matching id could not be found.");
        }
    }

}

