package io.catalyte.demo;

import io.catalyte.demo.patients.PatientRepository;
import io.catalyte.demo.patients.PatientServiceImpl;
import io.catalyte.demo.patients.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class PatientServiceImplTests {

	@InjectMocks
	PatientServiceImpl patientService;

	@Mock
	PatientRepository patientRepository;

	Patient testPatient;
	List<Patient> patientList;

	@BeforeEach
	public void setUp() {

		testPatient = new Patient();
		testPatient.setId(1);
		testPatient.setFirstName("John");
		testPatient.setLastName("Doe");
		testPatient.setSsn("123-45-6789");
		testPatient.setEmail("johndoe@example.com");
		testPatient.setStreet("123 Main St");
		testPatient.setCity("Anytown");
		testPatient.setState("CA");
		testPatient.setZip("12345");
		testPatient.setAge((short) 30);
		testPatient.setHeight((short) 70);
		testPatient.setWeight((short) 150);
		testPatient.setGender("Male");
		testPatient.setInsurance("Health Insurance");

		patientList = new ArrayList<>();
		patientList.add(testPatient);
	}

	@Test
	public void getPatients_nonEmptyList() {
		when(patientRepository.findAll()).thenReturn(patientList);

		List<Patient> patients = patientService.getPatients();

		assertNotNull(patients);
		assertEquals(1, patients.size());
		verify(patientRepository, times(1)).findAll();
	}

	@Test
	public void getPatientById_withValidId() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));

		Patient foundPatient = patientService.getPatientById(1);

		assertNotNull(foundPatient);
		assertEquals("John", foundPatient.getFirstName());
		verify(patientRepository, times(1)).findById(1);
	}

	@Test
	public void getPatientById_withInvalidId_ShouldThrow404Exception() {
		when(patientRepository.findById(1)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.getPatientById(1));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("Patient not found.", exception.getReason());
		verify(patientRepository, times(1)).findById(1);
	}

	@Test
	public void createPatient_withValidValues() {
		when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

		Patient createdPatient = patientService.createPatient(testPatient);

		assertNotNull(createdPatient);
		assertEquals("John", createdPatient.getFirstName());
		verify(patientRepository, times(1)).save(any(Patient.class));
	}

	@Test
	public void createPatient_DuplicateSSNEmail_shouldThrow409Exception() {
		patientList.add(new Patient(2, "Jane", "Doe", "123-45-6789", "janedoe@example.com", "456 Other St", "Othertown", "NY", "54321", (short) 28, (short) 68, (short) 140, "Female", "Health Insurance"));

		when(patientRepository.findAll()).thenReturn(patientList);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.createPatient(testPatient));

		assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
		assertEquals("Patient with this ssn already exists. Patient with this email already exists.", exception.getReason());
		verify(patientRepository, times(0)).save(any(Patient.class));
	}

	@Test
	public void createPatient_InvalidValues_shouldThrow400Exception() {
		Patient invalidPatient = getPatient();

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.createPatient(invalidPatient));

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		assertTrue(exception.getReason().contains("First Name field is empty"));
		assertTrue(exception.getReason().contains("The email is not in the right format: x@x.x"));
		verify(patientRepository, times(0)).save(any(Patient.class));
	}

	private static Patient getPatient() {
		Patient invalidPatient = new Patient();
		invalidPatient.setFirstName("");
		invalidPatient.setLastName("Doe");
		invalidPatient.setSsn("123-45-6789");
		invalidPatient.setEmail("invalid-email-format");
		invalidPatient.setStreet("123 Main St");
		invalidPatient.setCity("Anytown");
		invalidPatient.setState("CA");
		invalidPatient.setZip("12345");
		invalidPatient.setAge((short) 30);
		invalidPatient.setHeight((short) 70);
		invalidPatient.setWeight((short) 150);
		invalidPatient.setGender("Male");
		invalidPatient.setInsurance("Health Insurance");
		return invalidPatient;
	}

	@Test
	public void editPatient_withValidValues() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));
		when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

		Patient updatedPatient = patientService.editPatient(testPatient, 1);

		assertNotNull(updatedPatient);
		assertEquals("John", updatedPatient.getFirstName());
		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(1)).save(any(Patient.class));
	}

	@Test
	public void testEditPatient_NotFound_shouldThrow404Exception() {
		when(patientRepository.findById(1)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.editPatient(testPatient, 1));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("The patient was not found", exception.getReason());
		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(0)).save(any(Patient.class));
	}

	@Test
	public void editPatient_DuplicateSSNEmail_shouldThrow409Exception() {
		Patient anotherPatient = new Patient();
		anotherPatient.setId(2);
		anotherPatient.setFirstName("Jane");
		anotherPatient.setLastName("Doe");
		anotherPatient.setSsn("987-65-4321");
		anotherPatient.setEmail("jane.doe@example.com");
		patientList.add(anotherPatient);

		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));
		when(patientRepository.findAll()).thenReturn(patientList);

		Patient patientToUpdate = new Patient();
		patientToUpdate.setId(1);
		patientToUpdate.setFirstName("John");
		patientToUpdate.setLastName("Doe");
		patientToUpdate.setSsn("987-65-4321"); // Duplicate SSN
		patientToUpdate.setEmail("john.doe@example.com");

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.editPatient(patientToUpdate, 1));

		assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
		assertTrue(exception.getReason().contains("Patient with this ssn already exists."));
		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(0)).save(any(Patient.class));
	}

	@Test
	public void editPatient_InvalidValues_shouldThrow400Exception() {
		Patient invalidPatient = getInvalidPatient();

		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.editPatient(invalidPatient, 1));

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		assertTrue(exception.getReason().contains("Age must be between 0 and 120"));
		assertTrue(exception.getReason().contains("Insurance contains invalid characters"));
		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(0)).save(any(Patient.class));
	}

	private static Patient getInvalidPatient() {
		Patient invalidPatient = new Patient();
		invalidPatient.setFirstName("Marcy");
		invalidPatient.setLastName("Doe");
		invalidPatient.setSsn("123-45-6789");
		invalidPatient.setEmail("marcydoe@email.com");
		invalidPatient.setStreet("123 Main St");
		invalidPatient.setCity("Anytown");
		invalidPatient.setState("CA");
		invalidPatient.setZip("12345-1234");
		invalidPatient.setAge((short) 122);
		invalidPatient.setHeight((short) 70);
		invalidPatient.setWeight((short) 150);
		invalidPatient.setGender("Female");
		invalidPatient.setInsurance("Health*Insurance");
		return invalidPatient;
	}


	@Test
	public void deletePatientById_withValidID() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(testPatient));

		patientService.deletePatientById(1);

		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(1)).deleteById(1);
	}

	@Test
	public void deletePatientById_shouldThrow404Exception() {
		when(patientRepository.findById(1)).thenReturn(Optional.empty());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
			patientService.deletePatientById(1));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("Patient with matching id could not be found.", exception.getReason());
		verify(patientRepository, times(1)).findById(1);
		verify(patientRepository, times(0)).deleteById(1);
	}
}
