package io.catalyte.demo.vendor;

import static org.junit.jupiter.api.Assertions.*;

import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

public class VendorValidationTest {
    @Mock
    private VendorValidation validator = new VendorValidation();
    private Address address;
    private Contact contact;

    @BeforeEach
    public void setUp() {

        address = new Address();
        address.setStreet("1 Street1");
        address.setStreet2("ap5");
        address.setCity("Niles");
        address.setState("IL");
        address.setZipCode("12345");

        contact = new Contact();
        contact.setContactName("First Last");
        contact.setTitleOrRole("Manager");
        contact.setPhone("1234567891");
        contact.setEmail("valid@ex.com");
    }

    @Test
    public void testNameValidation_NullName_returnsError() {
        List<String> result = validator.nameValidation(null);
        assertEquals(1, result.size());
        assertTrue(result.contains("Name field is null"));
    }

    @Test
    public void testNameValidation_EmptyName() {
        List<String> result = validator.nameValidation("");
        assertEquals(1, result.size());
        assertTrue(result.contains("Name field is empty"));
    }

    //Take a look at this test - should return true, but returns false instead
    @Test
    public void testNameValidation_TooLongName() {
        String invalidName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> result = validator.nameValidation(invalidName);
        assertEquals(1,result.size());
        assertTrue(result.contains("Please enter a name shorter than 50 characters"));
    }

    @Test
    public void testNameValidation_ValidName() {
        List<String> result = validator.nameValidation("VendorName");
        assertEquals(0, result.size());
    }

    @Test
    public void testAddressValidation_AllFieldsValid() {
        List<String> result = validator.addressValidation(address);
        assertEquals(0, result.size());
    }

    @Test
    public void testAddressValidation_Street1Null() {
        address.setStreet(null);
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Street1 field is null"));
    }

    @Test
    public void testAddressValidation_Street1Empty() {
        address.setStreet("");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Street1 field is empty"));
    }

    @Test
    public void testAddressValidation_StateNull() {
        address.setState(null);
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("State field is null"));
    }

    @Test
    public void testAddressValidation_StateEmpty() {
        address.setState("");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("State field is empty"));
    }

    @Test
    public void testAddressValidation_StateInvalid() {
        address.setState("X");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Please add the right abbreviation of the state: two uppercase letters"));
    }

    @Test
    public void testAddressValidation_CityNull() {
        address.setCity(null);
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("City field is null"));
    }

    @Test
    public void testAddressValidation_CityEmpty() {
        address.setCity("");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("City field is empty"));
    }

    @Test
    public void testAddressValidation_ZipcodeNull() {
        address.setZipCode(null);
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Zipcode field is null"));
    }

    @Test
    public void testAddressValidation_ZipcodeEmpty() {
        address.setZipCode("");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Zipcode field is empty"));
    }

    @Test
    public void testAddressValidation_ZipcodeInvalid() {
        address.setZipCode("11g23");
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Zipcode must be 5 numerical digits"));
    }

    @Test
    public void testAddressValidation_StreetNull() {
        address.setStreet(null);
        List<String> result = validator.addressValidation(address);
        assertEquals(1, result.size());
        assertTrue(result.contains("Street1 field is null"));
    }


    @Test
    public void testEmailValidation_EmailNull() {
        List<String> result = validator.emailValidation(null);
        assertEquals(1, result.size());
        assertTrue(result.contains("Email field is null"));
    }

    @Test
    public void testEmailValidation_EmailEmpty() {
        List<String> result = validator.emailValidation("");
        assertEquals(1, result.size());
        assertTrue(result.contains("Email field is empty"));
    }

    @Test
    public void testEmailValidation_InvalidFormat() {
        List<String> result = validator.emailValidation("ex@");
        assertEquals(1, result.size());
        assertTrue(result.contains("The email is not in the right format: x@x.x"));
    }

    @Test
    public void testEmailValidation_ValidFormat() {
        List<String> result = validator.emailValidation("ex@tr.com");
        assertEquals(0, result.size());
    }


    @Test
    public void testPhoneValidation_PhoneNull() {
        List<String> result = validator.phoneValidation(null);
        assertEquals(1, result.size());
        assertTrue(result.contains("Phone field is null"));
    }

    @Test
    public void testPhoneValidation_PhoneEmpty() {
        List<String> result = validator.phoneValidation("");
        assertEquals(1, result.size());
        assertTrue(result.contains("Phone field is empty"));
    }

    @Test
    public void testPhoneValidation_InvalidFormat() {
        List<String> result = validator.phoneValidation("123-abc-7890");
        assertEquals(1, result.size());
        assertTrue(result.contains("The phone number is not in the right format: 999-999-9999 or 9999999999"));
    }

    @Test
    public void testPhoneValidation_ValidFormat_WithoutDashes() {
        List<String> result = validator.phoneValidation("1234567890");
        assertEquals(0, result.size());
    }

    @Test
    public void testPhoneValidation_ValidFormat_WithDashes() {
        List<String> result = validator.phoneValidation("123-456-7890");
        assertEquals(0, result.size());
    }


    @Test
    public void testContactValidation_AllFieldsValid() {
        List<String> result = validator.contactValidation(contact);
        assertEquals(0, result.size());
    }

    @Test
    public void testEmailValidation_EmailAndPhoneInvalid() {
        contact.setEmail("fgh@g.");
        contact.setPhone("77-888-9999");
        List<String> result = validator.contactValidation(contact);
        assertEquals(2, result.size());
        assertTrue(result.contains("The email is not in the right format: x@x.x"));
        assertTrue(result.contains("The phone number is not in the right format: 999-999-9999 or 9999999999"));
    }

    @Test
    public void testContactValidation_NeitherPhoneNorEmailProvided_returnErrorMessage() {
        contact.setPhone("");
        contact.setEmail("");
        List<String> result = validator.contactValidation(contact);
        assertTrue(result.contains("Either a valid email or phone number must be provided"));
    }

    @Test
    public void testEmailValidation_NoEmailNoPhone() {
        contact.setEmail("");
        contact.setPhone("");
        List<String> result = validator.contactValidation(contact);
        assertEquals(3, result.size());
        assertTrue(result.contains("Either a valid email or phone number must be provided"));
    }

    @Test
    public void testContactValidation_ContactNameNull() {
        contact.setContactName(null);
        List<String> result = validator.contactValidation(contact);
        assertEquals(1, result.size());
        assertTrue(result.contains("Contact name field is null"));
    }

    @Test
    public void testContactValidation_ContactNameEmpty() {
        contact.setContactName("");
        List<String> result = validator.contactValidation(contact);
        assertEquals(1, result.size());
        assertTrue(result.contains("Contact name field is empty"));
    }

    @Test
    public void testContactValidation_ContactNameInvalid() {
        contact.setContactName("FirstName");
        List<String> result = validator.contactValidation(contact);
        assertEquals(1, result.size());
        assertTrue(result.contains("Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes are allowed"));
    }

    @Test
    public void testContactValidation_RoleNull() {
        contact.setTitleOrRole(null);
        List<String> result = validator.contactValidation(contact);
        assertEquals(1, result.size());
        assertTrue(result.contains("Title/Role field is null"));
    }

    @Test
    public void testContactValidation_RoleEmpty() {
        contact.setTitleOrRole("");
        List<String> result = validator.contactValidation(contact);
        assertEquals(1, result.size());
        assertTrue(result.contains("Title/Role field is empty"));
    }
}