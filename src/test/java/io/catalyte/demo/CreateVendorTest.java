package io.catalyte.demo;

import io.catalyte.demo.vendor.CreateVendor;
import io.catalyte.demo.vendor.VendorRepository;
import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.PhoneNumberFormatter;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateVendorTest {

    @Mock
    private VendorRepository testRepository;

    private PhoneNumberFormatter testPhoneNumberFormatter = new PhoneNumberFormatter();

    private CreateVendor testCreateVendor;

    private Vendor testVendor;

    @BeforeEach
    public void setUp() {

        // Arrange
        testCreateVendor = new CreateVendor(testRepository, testPhoneNumberFormatter);

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
        testVendor.setName("Vendor Inc.");
        testVendor.setAddress(address);
        testVendor.setContact(contact);

        // Ensure testVendor is not null
        assertNotNull(testVendor);
    }

    @Test
    public void CreateVendor_validInputs_returnsPhoneNumberWithDashes() {

        // Arrange
        when(testRepository.save(any(Vendor.class))).thenReturn(testVendor);

        // Act
        Vendor result = testCreateVendor.createVendor(testVendor);

        // Assert
        assertEquals("Vendor Inc.", result.getName());

    }

    @Test
    public void CreateVendor_unexpectedServerError() {

        // Arrange & Act
        when(testRepository.save(any(Vendor.class))).thenThrow(
                RuntimeException.class);

        // Assert
        assertThrows(ResponseStatusException.class, () -> testCreateVendor.createVendor(testVendor));
    }

}
