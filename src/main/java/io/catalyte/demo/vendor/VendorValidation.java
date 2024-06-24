package io.catalyte.demo.vendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Vendor;

/**
 * Provides various validation methods for validating vendor details.
 */
public class VendorValidation {
    final List<String> US_states = Arrays.asList("AL", "AK", "AZ", "AR",
            "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
            "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

    /**
     * Validates the vendor's name.
     *
     * @param name the name to be validated
     * @return a list of error messages if the name is invalid; otherwise, an empty list
     */
    public List<String> nameValidation(String name) {
        List<String> errors = new ArrayList<>();
        if (name == null) {
            errors.add("Name field is null");
        } else if (name.isBlank()) {
            errors.add("Name field is empty");
        } else if (name.length() >= 50) {
            errors.add("Please enter a name shorter than 50 characters");
        }
        return errors;
    }

    /**
     * Validates the vendor's address.
     *
     * @param address the address to be validated
     * @return a list of error messages if the address is invalid; otherwise, an empty list
     */
    public List<String> addressValidation(Address address) {
        List<String> errors = new ArrayList<>();
        if (address.getStreet() == null) {
            errors.add("Street1 field is null");
        } else if (address.getStreet().isBlank()) {
            errors.add("Street1 field is empty");
        }
        if (address.getState() == null) {
            errors.add("State field is null");
        } else if (address.getState().isBlank()) {
            errors.add("State field is empty");
        } else if (!US_states.contains(address.getState())) {
            errors.add("Please add the right abbreviation of the state: two uppercase letters");
        }
        if (address.getCity() == null) {
            errors.add("City field is null");
        } else if (address.getCity().isBlank()) {
            errors.add("City field is empty");
        }
        if (address.getZipCode() == null) {
            errors.add("Zipcode field is null");
        } else if (address.getZipCode().isBlank()) {
            errors.add("Zipcode field is empty");
        } else if (!address.getZipCode().matches("\\d{5}")) {
            errors.add("Zipcode must be 5 numerical digits");
        }
        return errors;
    }

    /**
     * Validates the vendor's email.
     *
     * @param email the email to be validated
     * @return a list of error messages if the email is invalid; otherwise, an empty list
     */
    public List<String> emailValidation(String email) {
        List<String> errors = new ArrayList<>();
        String regex = "^[^@]+@[^@]+\\.[^@]+$";
        if (email == null) {
            errors.add("Email field is null");
        } else if (email.isBlank()) {
            errors.add("Email field is empty");
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                errors.add("The email is not in the right format: x@x.x");
            }
        }
        return errors;
    }

    /**
     * Validates the vendor's phone number.
     *
     * @param phone the phone number to be validated
     * @return a list of error messages if the phone number is invalid; otherwise, an empty list
     */
    public List<String> phoneValidation(String phone) {
        List<String> errors = new ArrayList<>();
        String regex = "^\\(?\\d{3}\\)?[-]?\\d{3}[-]?\\d{4}$";
        if (phone == null) {
            errors.add("Phone field is null");
        } else if (phone.isBlank()) {
            errors.add("Phone field is empty");
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                errors.add("The phone number is not in the right format: 999-999-9999 or 9999999999");
            }
        }
        return errors;
    }

    /**
     * Validates the vendor's contact information.
     *
     * @param contact the contact information to be validated
     * @return a list of error messages if the contact information is invalid; otherwise, an empty list
     */
    public List<String> contactValidation(Contact contact) {
        List<String> errors = new ArrayList<>();
        errors.addAll(emailValidation(contact.getEmail()));
        errors.addAll(phoneValidation(contact.getPhone()));

        boolean isEmailProvided = contact.getEmail() != null && !contact.getEmail().isBlank();
        boolean isPhoneProvided = contact.getPhone() != null && !contact.getPhone().isBlank();

        if (!isEmailProvided && !isPhoneProvided) {
            errors.add("Either a valid email or phone number must be provided");
        }

        if (contact.getContactName() == null) {
            errors.add("Contact name field is null");
        } else if (contact.getContactName().isBlank()) {
            errors.add("Contact name field is empty");
        } else if (!contact.getContactName().matches("^[a-zA-Z'-]+\\s[a-zA-Z'-]+$")) {
            errors.add("Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes are allowed");
        }

        if (contact.getTitleOrRole() == null) {
            errors.add("Title/Role field is null");
        } else if (contact.getTitleOrRole().isBlank()) {
            errors.add("Title/Role field is empty");
        }

        return errors;
    }

    /**
     * Validates the vendor's details.
     *
     * @param vendor the vendor to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validateVendor(Vendor vendor) {
        List<String> errors = new ArrayList<>();
        errors.addAll(nameValidation(vendor.getName()));
        errors.addAll(addressValidation(vendor.getAddress()));
        errors.addAll(contactValidation(vendor.getContact()));

        return errors.toArray(new String[0]);
    }
}
