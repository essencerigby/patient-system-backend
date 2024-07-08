package io.catalyte.demo;

import io.catalyte.demo.movies.MoviesServiceImpl;
import io.catalyte.demo.movies.MoviesRepository;
import io.catalyte.demo.movies.moviesEntity.Movies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class MoviesServiceImplTests {

	@InjectMocks
	MoviesServiceImpl vendorService;

	@Mock
	MoviesRepository moviesRepository;

	Movies testMovies;
	Movies testMovies2;
	Movies testMoviesToEdit;
	List<Movies> testMoviesList;

	@BeforeEach
	public void setUp() {
		testMovies = new Movies();
		testMovies.setId(1);
		testMovies.setTitle("Movies Inc.");
		testMovies.setGenre("Sci-fi");
		testMovies.setDirector("William Shakespeare");
		testMovies.setDailyRentalCost(new BigDecimal("16.25"));

		testMovies2 = new Movies();
		testMovies2.setId(2);
		testMovies2.setTitle("Second Movies Inc.");
		testMovies2.setGenre("Romance");
		testMovies2.setDirector("Mindy Kaling");
		testMovies2.setDailyRentalCost(new BigDecimal("3.25"));

		testMoviesToEdit = new Movies("Sleep", "Documentary", "Lisa Smith", new BigDecimal("4.25"));
		testMoviesToEdit.setId(2);
	}

	@Test
	public void getMovies_EmptyList() {
		when(moviesRepository.findAll()).thenReturn(Arrays.asList());

		List<Movies> movies = vendorService.getMovies();

		assertNotNull(movies);
		assertEquals(0, movies.size());
	}

	@Test
	public void getMovies_NonEmptyList() {
		List<Movies> expectedMovies = Arrays.asList(
				testMovies,
				testMovies2
		);
		when(moviesRepository.findAll()).thenReturn(expectedMovies);

		List<Movies> movies = vendorService.getMovies();

		assertNotNull(movies);
		assertEquals(2, movies.size());
		assertEquals(expectedMovies, movies);
	}

	@Test
	public void getMovieById_withValidId_returnsMovie() {
		when(moviesRepository.findById(1)).thenReturn(Optional.of(testMovies));

		Movies result = vendorService.getMoviesById(1);

		assertEquals(testMovies, result, "Movies name mismatch");
	}

	@Test
	public void getMoviesById_withInvalidId_returnsError() {
		int invalidId = 2;
		when(moviesRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			vendorService.getMoviesById(invalidId);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Expected NOT_FOUND status");
		assertEquals("Movies not found.", exception.getReason(), "Expected error message mismatch");
	}

	@Test
	public void createMovie_validInputs_returnsCreatedMovie() {
		when(moviesRepository.save(any(Movies.class))).thenReturn(testMovies);

		Movies result = vendorService.createMovie(testMovies);

		assertEquals(testMovies.getTitle(), result.getTitle());
	}

	@Test
	public void createMovie_invalidInputs_throwsException() {
		Movies invalidMovie = new Movies("", "", "", null);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			vendorService.createMovie(invalidMovie);
		});

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode(), "Expected BAD_REQUEST status");
	}

	@Test
	public void createMovie_unexpectedServerError() {
		when(moviesRepository.save(any(Movies.class))).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> vendorService.createMovie(testMovies));
	}

	@Test
	public void editMovie_whenMovieIdIsValid_shouldReturnUpdatedMovie() {
		when(moviesRepository.findById(1)).thenReturn(Optional.of(testMovies));
		when(moviesRepository.save(any(Movies.class))).thenReturn(testMoviesToEdit);

		Movies editedMovies = vendorService.editMovie(testMoviesToEdit, 1);

		assertEquals(testMoviesToEdit.getTitle(), editedMovies.getTitle());
		assertEquals(testMoviesToEdit.getGenre(), editedMovies.getGenre());
		assertEquals(testMoviesToEdit.getDirector(), editedMovies.getDirector());
		assertEquals(testMoviesToEdit.getDailyRentalCost(), editedMovies.getDailyRentalCost());
	}

	@Test
	public void editMovie_whenMovieIdIsNotValid_shouldThrow404Exception() {
		int invalidMovieId = 999;
		when(moviesRepository.findById(invalidMovieId)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			vendorService.editMovie(testMoviesToEdit, invalidMovieId);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("404 NOT_FOUND \"The Movies was not found\"", exception.getMessage());
	}

	@Test
	public void deleteMovie_withValidId_deletesMovie() {
		int id = testMovies.getId();
		when(moviesRepository.findById(id)).thenReturn(Optional.of(testMovies));
		doNothing().when(moviesRepository).deleteById(id);

		vendorService.deleteMovieById(id);

		verify(moviesRepository, times(1)).deleteById(id);
	}

	@Test
	public void deleteMovie_withInvalidId_throwsResponseStatusException() {
		int invalidId = 200;
		when(moviesRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			vendorService.deleteMovieById(invalidId);
		});

		assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode(), "Expected NOT_FOUND status");
		assertEquals("Movies with matching id could not be found.", e.getReason(), "Expected error message mismatch");
	}
}
