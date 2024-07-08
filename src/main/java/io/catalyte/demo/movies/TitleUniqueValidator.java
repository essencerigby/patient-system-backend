package io.catalyte.demo.movies;

import io.catalyte.demo.movies.moviesEntity.Movies;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Provides validation to check if a movie title already exists within a list of movies.
 */
@Component
public class TitleUniqueValidator {

    /**
     * Checks if the given title is unique within the provided list of vendors.
     *
     * @param title the title to be checked for uniqueness
     * @param moviesList the list of vendors to check against
     * @return a message indicating the result of the validation. If the title is unique, an empty string is returned.
     *         If a movie with the given title already exists, a message indicating this is returned.
     */
    public String isTitleUnique(String title, List<Movies> moviesList) {

        for (Movies movies : moviesList) {
                if (movies.getTitle().equals(title)) {
                    return "Movies with this title already exists";
                }
            }
        return "";
    }
}
