package io.catalyte.demo;

import io.catalyte.demo.vendor.VendorRepository;
import io.catalyte.demo.vendor.VendorServiceImpl;
import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class VendorServiceImplTests {

	@InjectMocks
	VendorServiceImpl vendorService;

	@Mock
	VendorRepository vendorRepository;

	Vendor testVendor;

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
		testVendor.setName("Starbucks");
		testVendor.setContact(contact);
		testVendor.setAddress(address);
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

}
