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

//    @Test
//    public void testNameValidation_EmptyName() {
//        String result = validator.nameValidation("");
//        assertEquals("~Name field is empty~", result);
//    }
//
//    @Test
//    public void testNameValidation_TooLongName() {
//        String result = validator.nameValidation("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        assertEquals("~Please enter a shorter name~", result);
//    }
//
//    @Test
//    public void testNameValidation_ValidName() {
//        String result = validator.nameValidation("VendorName");
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testAddressValidation_AllFieldsValid() {
//        String result = validator.addressValidation(address);
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testAddressValidation_Street1Null() {
//        address.setStreet(null);
//        String result = validator.addressValidation(address);
//        assertEquals("~Street1 field is null~", result);
//    }
//
//    @Test
//    public void testAddressValidation_Street1Empty() {
//        address.setStreet("");
//        String result = validator.addressValidation(address);
//        assertEquals("~Street1 field is empty~", result);
//    }
//
//    @Test
//    public void testAddressValidation_StateNull() {
//        address.setState(null);
//        String result = validator.addressValidation(address);
//        assertEquals("~State field is null~", result);
//    }
//
//    @Test
//    public void testAddressValidation_StateEmpty() {
//        address.setState("");
//        String result = validator.addressValidation(address);
//        assertEquals("~State field is empty~", result);
//    }
//
//    @Test
//    public void testAddressValidation_StateInvalid() {
//        address.setState("X");
//        String result = validator.addressValidation(address);
//        assertEquals("~Please add the right abbreviation of the state: two uppercase letters~", result);
//    }
//
//    @Test
//    public void testAddressValidation_CityNull() {
//        address.setCity(null);
//        String result = validator.addressValidation(address);
//        assertEquals("~City field is null~", result);
//    }
//
//    @Test
//    public void testAddressValidation_CityEmpty() {
//        address.setCity("");
//        String result = validator.addressValidation(address);
//        assertEquals("~City field is empty~", result);
//    }
//
//    @Test
//    public void testAddressValidation_ZipcodeNull() {
//        address.setZipCode(null);
//        String result = validator.addressValidation(address);
//        assertEquals("~Zipcode field is null~", result);
//    }
//
//    @Test
//    public void testAddressValidation_ZipcodeEmpty() {
//        address.setZipCode("");
//        String result = validator.addressValidation(address);
//        assertEquals("~Zipcode field is empty~", result);
//    }
//
//    @Test
//    public void testAddressValidation_ZipcodeInvalid() {
//        address.setZipCode("11g23");
//        String result = validator.addressValidation(address);
//        assertEquals("~Zipcode must be 5 numerical digits~", result);
//    }
//
//    @Test
//    public void testEmailValidation_EmailNull() {
//        String result = validator.emailValidation(null);
//        assertEquals("~Email field is null~", result);
//    }
//
//    @Test
//    public void testEmailValidation_EmailEmpty() {
//        String result = validator.emailValidation("");
//        assertEquals("~Email field is empty~", result);
//    }
//
//    @Test
//    public void testEmailValidation_InvalidFormat() {
//        String result = validator.emailValidation("ex@");
//        assertEquals("The email is not in the right format: x@x.x", result);
//    }
//
//    @Test
//    public void testEmailValidation_ValidFormat() {
//        String result = validator.emailValidation("ex@tr.com");
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testPhoneValidation_PhoneNull() {
//        String result = validator.phoneValidation(null);
//        assertEquals("~Phone field is null~", result);
//    }
//
//    @Test
//    public void testPhoneValidation_PhoneEmpty() {
//        String result = validator.phoneValidation("");
//        assertEquals("~Phone field is empty~", result);
//    }
//
//    @Test
//    public void testPhoneValidation_InvalidFormat() {
//        String result = validator.phoneValidation("123-abc-7890");
//        assertEquals("~The phone number is not in the right format: 999-999-9999 or 9999999999~", result);
//    }
//
//    @Test
//    public void testPhoneValidation_ValidFormat_WithoutDashes() {
//        String result = validator.phoneValidation("1234567890");
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testPhoneValidation_ValidFormat_WithDashes() {
//        String result = validator.phoneValidation("123-456-7890");
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testContactValidation_AllFieldsValid() {
//        String result = validator.contactValidation(contact);
//        assertEquals("", result);
//    }
//
//    @Test
//    public void testEmailValidation_EmailAndPhoneInvalid() {
//        contact.setEmail("fgh@g.");
//        contact.setPhone("77-888-9999");
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Either a valid email or phone number must be provided~"));
//        assertTrue(result.contains("The email is not in the right format: x@x.x"));
//        assertTrue(result.contains("~The phone number is not in the right format: 999-999-9999 or 9999999999~"));
//    }
//
//    @Test
//    public void testEmailValidation_NoEmailNoPhone() {
//        contact.setEmail("");
//        contact.setPhone("");
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Either a valid email or phone number must be provided~"));
//    }
//
//    @Test
//    public void testContactValidation_ContactNameNull() {
//        contact.setContactName(null);
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Contact name field is null~"));
//    }
//
//    @Test
//    public void testContactValidation_ContactNameEmpty() {
//        contact.setContactName("");
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Contact name field is empty~"));
//    }
//
//    @Test
//    public void testContactValidation_ContactNameInvalid() {
//        contact.setContactName("FirstName");
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes allowed~"));
//    }
//
//    @Test
//    public void testContactValidation_RoleNull() {
//        contact.setTitleOrRole(null);
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Title/Role field is null~"));
//    }
//
//    @Test
//    public void testContactValidation_RoleEmpty() {
//        contact.setTitleOrRole("");
//        String result = validator.contactValidation(contact);
//        assertTrue(result.contains("~Title/role field is empty~"));
//    }
}