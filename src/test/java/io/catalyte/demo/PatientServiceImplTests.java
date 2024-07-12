package io.catalyte.demo;

import io.catalyte.demo.patients.PatientServiceImpl;
import io.catalyte.demo.patients.PatientRepository;
import io.catalyte.demo.patients.patientEntity.Patient;
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

class PatientServiceImplTests {

	@InjectMocks
	PatientServiceImpl moviesService;

	@Mock
	PatientRepository patientRepository;

	Patient testPatient;
	Patient testPatient2;
	Patient testPatientToEdit;
	List<Patient> testPatientList;

	@BeforeEach
	public void setUp() {
		testPatient = new Patient();
		testPatient.setId(1);
		testPatient.setSsn("Patient Inc.");
		testPatient.setGender("Sci-fi");
		testPatient.setInsurance("William Shakespeare");
		testPatient.setDailyRentalCost(new BigDecimal("16.25"));

		testPatient2 = new Patient();
		testPatient2.setId(2);
		testPatient2.setSsn("Second Patient Inc.");
		testPatient2.setGender("Romance");
		testPatient2.setInsurance("Mindy Kaling");
		testPatient2.setDailyRentalCost(new BigDecimal("3.25"));

		testPatientToEdit = new Patient("Sleep", "Documentary", "Lisa Smith", new BigDecimal("4.25"));
		testPatientToEdit.setId(2);
	}

	@Test
	public void getPatients_EmptyList() {
		when(patientRepository.findAll()).thenReturn(Arrays.asList());

		List<Patient> movies = moviesService.getPatients();

		assertNotNull(movies);
		assertEquals(0, movies.size());
	}

	@Test
	public void getPatients_NonEmptyList() {
		List<Patient> expectedMovies = Arrays.asList(
				testPatient,
				testPatient2
		);
		when(patientRepository.findAll()).thenReturn(expectedMovies);

		List<Patient> movies = moviesService.getPatients();

		assertNotNull(movies);
		assertEquals(2, movies.size());
		assertEquals(expectedMovies, movies);
	}

	@Test
	public void getMovieById_withValidId_returnsMovie() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));

		Patient result = moviesService.getPatientById(1);

		assertEquals(testPatient, result, "Movie title mismatch");
	}

	@Test
	public void getPatientsById_withInvalidId_returnsError() {
		int invalidId = 2;
		when(patientRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			moviesService.getPatientById(invalidId);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Expected NOT_FOUND status");
		assertEquals("Movie not found.", exception.getReason(), "Expected error message mismatch");
	}

	@Test
	public void createMovie_validInputs_returnsCreatedPatient() {
		when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

		Patient result = moviesService.createPatient(testPatient);

		assertEquals(testPatient.getSsn(), result.getSsn());
	}

	@Test
	public void createPatient_invalidInputs_throwsException() {
		Patient invalidMovie = new Patient("", "", "", null);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			moviesService.createPatient(invalidMovie);
		});

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode(), "Expected BAD_REQUEST status");
	}

	@Test
	public void createPatient_unexpectedServerError() {
		when(patientRepository.save(any(Patient.class))).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> moviesService.createPatient(testPatient));
	}

	@Test
	public void editMovie_whenMovieIdIsValid_shouldReturnUpdatedPatient() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));
		when(patientRepository.save(any(Patient.class))).thenReturn(testPatientToEdit);

		Patient editedPatient = moviesService.editPatient(testPatientToEdit, 1);

		assertEquals(testPatientToEdit.getSsn(), editedPatient.getSsn());
		assertEquals(testPatientToEdit.getGender(), editedPatient.getGender());
		assertEquals(testPatientToEdit.getInsurance(), editedPatient.getInsurance());
		assertEquals(testPatientToEdit.getDailyRentalCost(), editedPatient.getDailyRentalCost());
	}

	@Test
	public void editMovie_whenPatientIdIsNotValid_shouldThrow404Exception() {
		int invalidMovieId = 999;
		when(patientRepository.findById(invalidMovieId)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			moviesService.editPatient(testPatientToEdit, invalidMovieId);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("404 NOT_FOUND \"The movie was not found\"", exception.getMessage());
	}

	@Test
	public void deleteMovie_withValidId_deletesMovie() {
		int id = testPatient.getId();
		when(patientRepository.findById(id)).thenReturn(Optional.of(testPatient));
		doNothing().when(patientRepository).deleteById(id);

		moviesService.deletePatientById(id);

		verify(patientRepository, times(1)).deleteById(id);
	}

	@Test
	public void deleteMovie_withInvalidId_throwsResponseStatusException() {
		int invalidId = 200;
		when(patientRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			moviesService.deletePatientById(invalidId);
		});

		assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode(), "Expected NOT_FOUND status");
		assertEquals("Movie with matching id could not be found.", e.getReason(), "Expected error message mismatch");
	}
}
