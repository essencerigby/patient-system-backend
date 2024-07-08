package io.catalyte.demo.movies;

import io.catalyte.demo.movies.moviesEntity.Address;
import io.catalyte.demo.movies.moviesEntity.Contact;
import io.catalyte.demo.movies.moviesEntity.Movies;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class TitleUniqueValidatorTest {
    TitleUniqueValidator validator = new TitleUniqueValidator();

    @Test
    public void testIsTitleUnique_TitleNotInList_ShouldReturnEmptyString() {
        List<Movies> moviesList = new ArrayList<>();
        moviesList.add(new Movies("ExistingVendor", "Documentary", "Joe Doe", new BigDecimal("6.50")));
        String result = validator.isTitleUnique("NewVendor", moviesList);
        assertEquals("", result);
    }

    @Test
    public void testIsTitleUnique_TitleInList_ShouldReturnErrorMessage() {
        List<Movies> moviesList = new ArrayList<>();
        moviesList.add(new Movies("ExistingVendor", "Documentary", "Joe Doe", new BigDecimal("6.50")));
        String result = validator.isTitleUnique("ExistingVendor", moviesList);
        assertEquals("Movies with this name already exists", result);
    }
}
