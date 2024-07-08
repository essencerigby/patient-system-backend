package io.catalyte.demo.movies;

import java.util.List;

import io.catalyte.demo.movies.moviesEntity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MoviesController {
    /**
     * A controller class to map CRUD functions from MoviesService to RESTful endpoints
     * Autowired to MoviesServiceImpl (service class)
     * */

    private final MoviesService moviesService;

    /**
     * Injecting MoviesService implementation
     * @param moviesService - the service for performing CRUD methods on Movies instances
     * */
    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    /**
     * Retrieves all movies
     * @return list of all movies
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movies> getMovies() {
        return moviesService.getMovies();
    }

    /**
     * Retrieves a movies of specified ID
     * @param id is ID of movies
     * @return movies with specified ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movies getMovieById(@PathVariable int id) {
        return moviesService.getMoviesById(id);
    }

    /**
     * Creates a movies so long as properties are valid
     * @param moviesToCreate - the movies whose creation is requested
     * @return created movies
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movies createMovie(@RequestBody Movies moviesToCreate) {
        return moviesService.createMovie(moviesToCreate);
    }

    /**
     * Edits a movies with specified ID
     * @param id is ID of movies
     * @return edited movies and its ID
     * */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movies editMovie(@RequestBody Movies moviesToEdit, @PathVariable int id) {
        return moviesService.editMovie(moviesToEdit, id);
    }

    /**
     * Deletes a movies with specified ID
     * @param id is ID of movies
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable int id) {
        moviesService.deleteMovieById(id);
    }
}

