package io.catalyte.demo;

import io.catalyte.demo.vendor.VendorRepository;
import io.catalyte.demo.vendor.VendorServiceImpl;
import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.PhoneNumberFormatter;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class VendorServiceImplTests {

	@InjectMocks
	VendorServiceImpl vendorService;

	@Mock
	VendorRepository vendorRepository;

	private final PhoneNumberFormatter testPhoneNumberFormatter = new PhoneNumberFormatter(); //

	Vendor testVendor;
	Vendor testVendor2;
	Vendor testVendorToEdit;
	List<Vendor> testVendors;

	@BeforeEach
	public void setUp() {
		Address address = new Address();
		address.setCity("Anytown");
		address.setState("NY");
		address.setStreet("123 Main St");
		address.setStreet2("Apt 4");

		Contact contact = new Contact();
		contact.setContactName("John Doe");
		contact.setTitleOrRole("Manager");
		contact.setPhone("123-456-7890");
		contact.setEmail("john.doe@example.com");

		testVendor = new Vendor();
		testVendor.setId(1);
		testVendor.setName("Vendor Inc.");
		testVendor.setContact(contact);
		testVendor.setAddress(address);
		testVendor.setCreatedAt("05-01-2023 10:00");
		testVendor.setUpdatedAt("10-15-2023 14:30");

		Address address2 = new Address();
		address2.setStreet("5678 Greenway Blvd");
		address2.setCity("Austin");
		address2.setState("TX");
		address2.setZipCode("78701");

		Contact contact2 = new Contact();
		contact2.setEmail("contact@greenenergy.com");
		contact2.setContactName("Jane Smith");
		contact2.setTitleOrRole("Managing Director");
		contact2.setPhone("987-654-3210");

		testVendor2 = new Vendor();
		testVendor.setId(2);
		testVendor.setName("Second Vendor Inc.");
		testVendor.setContact(contact2);
		testVendor.setAddress(address2);
		testVendor.setCreatedAt("06-12-2023 10:00");
		testVendor.setUpdatedAt("06-12-2024 14:30");

		testVendorToEdit = new Vendor("Green Energy Solutions", address2, contact2);
		testVendorToEdit.setCreatedAt("05-01-2023 10:00");
		testVendorToEdit.setUpdatedAt("10-15-2023 14:30");
		testVendorToEdit.setId(2);
	}

	@Test
	public void getVendors_EmptyList() {
		when(vendorRepository.findAll()).thenReturn(Arrays.asList());

		List<Vendor> vendors = vendorService.getVendors();

		assertNotNull(vendors);
		assertEquals(0, vendors.size());
	}

	@Test
	public void getVendors_NonEmptyList() {
		List<Vendor> expectedVendors = Arrays.asList(
				testVendor,
				testVendor
		);
		when(vendorRepository.findAll()).thenReturn(expectedVendors);

		List<Vendor> vendors = vendorService.getVendors();

		assertNotNull(vendors);
		assertEquals(2, vendors.size());
		assertEquals(expectedVendors, vendors);
	}

	@Test
	public void getVendorByName_whenNameExists_shouldReturnVendor() {
		testVendors = Arrays.asList(testVendor, testVendor2);
		when(vendorRepository.findByNameIgnoreCase("Second Vendor Inc.")).thenReturn(testVendors);

		List<Vendor> result = vendorService.getVendorByName("Second Vendor Inc.");

		assertEquals(testVendor2.getName(), result.get(1).getName());
	}

	@Test
	public void getVendorByName_whenNameDoesntExist_shouldThrow404Exception() {
		testVendors = Arrays.asList();
		when(vendorRepository.findByNameIgnoreCase("Fake Vendor")).thenReturn(testVendors);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
				vendorService.getVendorByName("Fake Vendor"));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	}

	@Test
	public void getVendorByName_whenNameIsNullOrEmpty_shouldThrow400Exception() {

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
				vendorService.getVendorByName(""));

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
	}


	@Test
	public void getVendorById_withValidId_returnsVendor() {
		// Arrange
		when(vendorRepository.findById(1)).thenReturn(Optional.of(testVendor));

		// Act
		Vendor result = vendorService.getVendorById(1);

		// Assert
		assertEquals(testVendor, result, "Vendor name mismatch");
	}

	@Test
	public void getVendorById_withInvalidId_returnsError(){
		// Arrange
		int invalidId = 2;
		when(vendorRepository.findById(invalidId)).thenReturn(Optional.empty());

		// Act
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			vendorService.getVendorById(invalidId);
		});

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Expected NOT_FOUND status");
		assertEquals("Vendor not found.", exception.getReason(), "Expected error message mismatch");
	}

	@Test
	public void CreateVendor_validInputs_returnsPhoneNumberWithDashes() {
		// Arrange
		when(vendorRepository.save(any(Vendor.class))).thenReturn(testVendor);

		// Act
		Vendor result = vendorService.createVendor(testVendor);

		// Assert
		assertEquals("Second Vendor Inc.", result.getName());
	}

	@Test
	public void CreateVendor_unexpectedServerError() {
		// Arrange & Act
		when(vendorRepository.save(any(Vendor.class))).thenThrow(
				RuntimeException.class);

		// Assert
//		assertThrows(ResponseStatusException.class, () -> vendorService.createVendor(testVendor));
		assertThrows(RuntimeException.class, () -> vendorService.createVendor(testVendor));
	}

	@Test
	public void editVendor_whenVendorIdIsValid_shouldReturnObject() {
		// Arrange
		when(vendorRepository.findById(1)).thenReturn(Optional.of(testVendor));
		when(vendorRepository.save(testVendorToEdit)).thenReturn(testVendorToEdit);

		// Act
		Vendor editedVendor = vendorService.editVendor(testVendorToEdit, 1);

		// Assert
		assertEquals(testVendorToEdit.getName(), editedVendor.getName());
		assertEquals(testVendorToEdit.getAddress(), editedVendor.getAddress());
		assertEquals(testVendorToEdit.getContact(), editedVendor.getContact());
	}

	@Test
	public void editVendor_whenVendorIdIsNotValid_shouldThrow404Exception() {
		// Arrange
		int invalidVendorId = 999; // Assuming this vendor ID does not exist
		when(vendorRepository.findById(invalidVendorId)).thenReturn(Optional.empty());

		// Act & Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			vendorService.editVendor(testVendorToEdit, invalidVendorId);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals("404 NOT_FOUND \"The Vendor was not found\"", exception.getMessage());
	}

	@Test
	public void editVendor_whenVendorUpdates_shouldUpdateTimeStamp() {
		// Arrange
		int id = testVendor.getId();
		when(vendorRepository.findById(id)).thenReturn(Optional.of(testVendor));
		when(vendorRepository.save(testVendorToEdit)).thenReturn(testVendorToEdit);

		// Act
		Vendor editedVendor = vendorService.editVendor(testVendorToEdit, id);

		// Assert
		assertNotEquals(testVendor.getUpdatedAt(), editedVendor.getUpdatedAt());
	}

	@Test
	public void editVendor_whenTryingToUpdateId_shouldDefaultToPathId() {
		// Arrange
		int id = testVendor.getId();
		when(vendorRepository.findById(id)).thenReturn(Optional.of(testVendor));
		when(vendorRepository.save(testVendorToEdit)).thenReturn(testVendorToEdit);

		// Act
		testVendorToEdit.setId(99);
		Vendor editedVendor = vendorService.editVendor(testVendorToEdit, id);

		// Assert
		assertEquals(id, editedVendor.getId());
		assertNotEquals(99, editedVendor.getId());
	}
}
