package io.catalyte.demo.movies;

import io.catalyte.demo.movies.moviesEntity.Movies;

import java.util.List;

public interface MoviesService {

    // CRUD methods:

    List<Movies> getMovies();

    Movies getMoviesById(int id);

    Movies createMovie(Movies movieToCreate);

    Movies editMovie(Movies movieToEdit, int id);

    void deleteMovieById(int id);
}
