package io.catalyte.demo.movies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.catalyte.demo.movies.moviesEntity.Movies;
import org.springframework.stereotype.Component;

/**
 * Provides various validation methods for validating movies details.
 */
@Component
public class MoviesValidation {

    /**
     * Validates the movie's tile.
     *
     * @param title the tile to be validated
     * @return a list of error messages if the tile is invalid; otherwise, an empty list
     */
    public List<String> titleValidation(String title) {
        List<String> errors = new ArrayList<>();
        if (title == null) {
            errors.add("Title field is null");
        } else if (title.isBlank()) {
            errors.add("Title field is empty");
        } else if (title.length() > 50) {
            errors.add("Please enter a tile shorter than 50 characters");
        } else if (!title.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("Title contains invalid characters");
        }
        return errors;
    }

    public List<String> genreValidation(String genre) {
        List<String> errors = new ArrayList<>();
        if  (genre == null) {
            errors.add("Genre field is null");
        } else if (genre.isBlank()) {
            errors.add("Genre field is empty");
        } else if (genre.length() > 20) {
            errors.add("Please enter a genre less than 50 characters");
        } else if (!genre.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("Genre contains invalid characters");
        }
        return errors;
    }

    public List<String> directorValidation(String director) {
        List<String> errors = new ArrayList<>();
        if (director == null) {
            errors.add("Director field is null");
        } else if (director.isBlank()) {
            errors.add("Director field is empty");
        } else if (director.length() > 50) {
            errors.add("Please enter a director name shorter than 50 characters");
        } else if (!director.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("Director name contains invalid characters");
        }
        return errors;
    }

    public List<String> dailyRentalCostValidation(String dailyRentalCost) {
        List<String> errors = new ArrayList<>();
        if (dailyRentalCost == null) {
            errors.add("Daily rental cost field is null");
        } else {
            try {
                BigDecimal cost = new BigDecimal(dailyRentalCost);
                if (cost.scale() > 2) {
                    errors.add("Daily rental cost can only have up to two decimal places");
                }
                if (cost.compareTo(new BigDecimal("19.99")) > 0) {
                    errors.add("Daily rental cost exceeds the maximum allowed value of 19.99");
                }
            } catch (NumberFormatException e) {
                errors.add("Daily rental cost is not a valid decimal number");
            }
        }
        return errors;
    }

    /**
     * Validates the movie's details.
     *
     * @param movies the movies to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validateMovie(Movies movies) {
        List<String> errors = new ArrayList<>();
        errors.addAll(titleValidation(movies.getTitle()));
        errors.addAll(genreValidation(movies.getGenre()));
        errors.addAll(directorValidation(movies.getDirector()));
        errors.addAll(dailyRentalCostValidation(String.valueOf(movies.getDailyRentalCost())));
        return errors.toArray(new String[0]);
    }
}
