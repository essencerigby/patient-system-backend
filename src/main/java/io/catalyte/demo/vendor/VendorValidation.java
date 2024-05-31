package io.catalyte.demo.vendor;

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
     * @return an error message if the name is invalid; otherwise, an empty string
     */
    public String nameValidation(String name) {
        String err1 = "";
        if (name == null) {
            err1 += "~Name field is null~";
        } else if (name.isBlank()) {
            err1 += "~Name field is empty~";
        } else if (!name.matches("[a-zA-Z]+")) {
            err1 += "The name must have only alphabetic characters";
        } else if (name.length() >= 50) {
            err1 += "~Please enter a shorter name~";
        }
        return err1;
    }

    /**
     * Validates the vendor's address.
     *
     * @param address the address to be validated
     * @return an error message if the address is invalid; otherwise, an empty string
     */
    public String addressValidation(Address address) {
        String err2 = "";
        if (address.getStreet() == null) {
            err2 += "~Street1 field is null~";
        } else if (address.getStreet().isBlank()) {
            err2 += "~Street1 field is empty~";
        }
        if (address.getState() == null) {
            err2 += "~State field is null~";
        } else if (address.getState().isBlank()) {
            err2 += "~State field is empty~";
        } else if (!US_states.contains(address.getState())) {
            err2 += "~Please add the right abbreviation of the state: two uppercase letters~";
        }
        if (address.getCity() == null) {
            err2 += "~City field is null~";
        } else if (address.getCity().isBlank()) {
            err2 += "~City field is empty~";
        }
        if (address.getZipCode() == null) {
            err2 += "~Zipcode field is null~";
        } else if (address.getZipCode().isBlank()) {
            err2 += "~Zipcode field is empty~";
        } else if (!address.getZipCode().matches("\\d{5}")) {
            err2 += "~Zipcode must be 5 numerical digits~";
        }
        return err2;
    }

    /**
     * Validates the vendor's email.
     *
     * @param email the email to be validated
     * @return an error message if the email is invalid; otherwise, an empty string
     */
    public String emailValidation(String email) {
        String err3 = "";
        String regex = "^[^@]+@[^@]+\\.[^@]+$";
        if (email == null) {
            err3 += "~Email field is null~";
        } else if (email.isBlank()) {
            err3 += "~Email field is empty~";
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                err3 += "The email is not in the right format: x@x.x";
            }
        }
        return err3;
    }

    /**
     * Validates the vendor's phone number.
     *
     * @param phone the phone number to be validated
     * @return an error message if the phone number is invalid; otherwise, an empty string
     */
    public String phoneValidation(String phone) {
        String err4 = "";
        String regex = "^\\(?\\d{3}\\)?[-]?\\d{3}[-]?\\d{4}$";
        if (phone == null) {
            err4 += "~Phone field is null~";
        } else if (phone.isBlank()) {
            err4 += "~Phone field is empty~";
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                err4 += "~The phone number is not in the right format: 999-999-9999 or 9999999999~";
            }
        }
        return err4;
    }

    /**
     * Validates the vendor's contact information.
     *
     * @param contact the contact information to be validated
     * @return an error message if the contact information is invalid; otherwise, an empty string
     */
    public String contactValidation(Contact contact) {
        String err5 = "";
        String emailError = emailValidation(contact.getEmail());
        String phoneError = phoneValidation(contact.getPhone());
        boolean isEmailProvided = contact.getEmail() != null && !contact.getEmail().isBlank();
        boolean isPhoneProvided = contact.getPhone() != null && !contact.getPhone().isBlank();
        if (!emailError.isBlank() && !phoneError.isBlank()) {
            err5 += "~Either a valid email or phone number must be provided~";
            err5 += emailValidation(contact.getEmail());
            err5 += phoneValidation(contact.getPhone());
        } else {
            if (isEmailProvided && !emailError.isBlank()) {err5 += emailError;
            }
            if (isPhoneProvided && !phoneError.isBlank()) {err5 += phoneError;
            }
        }
        if (contact.getContactName() == null) {
            err5 += "~Contact name field is null~";
        } else if (contact.getContactName().isBlank()) {
            err5 += "~Contact name field is empty~";
        } else if (!contact.getContactName().matches("^[a-zA-Z'-]+\\s[a-zA-Z'-]+$")) {
            err5 += "~Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes allowed~";
        }
        if (contact.getTitleOrRole() == null) {
            err5 += "~Title/Role field is null~";
        } else if (contact.getTitleOrRole().isBlank()) {
            err5 += "~Title/role field is empty~";
        }
        return err5;
    }

    /**
     * Validates the vendor's timestamp.
     *
     * @param timestamp the timestamp to be validated
     * @return an error message if the timestamp is invalid; otherwise, an empty string
     */
    public String timestampValidation(String timestamp) {
        String err6 = "";
        String regex = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-\\d{4} (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$";
        if (timestamp == null) {
            err6 += "~Timestamp field is null~";
        } else if (timestamp.isBlank()) {
            err6 += "~Timestamp field is empty~";
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(timestamp);
            if (!matcher.matches()) {
                err6 += "The timestamp is not in the right format: mm-dd-yyyy hh:mm";
            }
        }
        return err6;
    }

    /**
     * Validates the vendor's details.
     *
     * @param vendor the vendor to be validated
     * @return a concatenation of error messages for invalid fields; otherwise, an empty string
     */
    public String validateVendor(Vendor vendor) {
            return nameValidation(vendor.getName()) + addressValidation(vendor.getAddress()) + contactValidation(vendor.getContact()) + timestampValidation(vendor.getCreatedAt());
        }
}