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
    // Create a StringBuilder object to gather error messages
    StringBuilder str = new StringBuilder();

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
    public StringBuilder nameValidation(String name) {
//        String err1 = "";
        str = new StringBuilder();
        if (name == null) {
//            err1 += "~Name field is null~";
            str.append("Name field is null");

        } else if (name.isBlank()) {
//            err1 += "~Name field is empty~";
            str.append("Name field is empty");
        } else if (name.length() >= 50) {
//            err1 += "~Please enter a shorter name~";
            str.append("Please enter a name shorter than 50 characters");
        }
        return str;
    }

    /**
     * Validates the vendor's address.
     *
     * @param address the address to be validated
     * @return an error message if the address is invalid; otherwise, an empty string
     */
    public StringBuilder addressValidation(Address address) {
//        String err2 = "";
        str = new StringBuilder();
        if (address.getStreet() == null) {
            str.append("Street1 field is null");
//            err2 += "~Street1 field is null~";
        } else if (address.getStreet().isBlank()) {
            str.append("Street1 field is empty");
//            err2 += "~Street1 field is empty~";
        }
        if (address.getState() == null) {
            str.append("State field is null");
//            err2 += "~State field is null~";
        } else if (address.getState().isBlank()) {
            str.append("State field is empty");
//            err2 += "~State field is empty~";
        } else if (!US_states.contains(address.getState())) {
            str.append("Please add the right abbreviation of the state: two uppercase letters");
//            err2 += "~Please add the right abbreviation of the state: two uppercase letters~";
        }
        if (address.getCity() == null) {
            str.append("City field is null");
//            err2 += "~City field is null~";
        } else if (address.getCity().isBlank()) {
            str.append("City field is empty");
//            err2 += "~City field is empty~";
        }
        if (address.getZipCode() == null) {
            str.append("Zipcode field is null");
//            err2 += "~Zipcode field is null~";
        } else if (address.getZipCode().isBlank()) {
            str.append("Zipcode field is empty");
//            err2 += "~Zipcode field is empty~";
        } else if (!address.getZipCode().matches("\\d{5}")) {
            str.append("Zipcode must be 5 numerical digits");
//            err2 += "~Zipcode must be 5 numerical digits~";
        }
        return str;
    }

    /**
     * Validates the vendor's email.
     *
     * @param email the email to be validated
     * @return an error message if the email is invalid; otherwise, an empty string
     */
    public StringBuilder emailValidation(String email) {
//        String err3 = "";
        str = new StringBuilder();
        String regex = "^[^@]+@[^@]+\\.[^@]+$";
        if (email == null) {
            str.append("Email field is null");
//            err3 += "~Email field is null~";
        } else if (email.isBlank()) {
            str.append("Email field is empty");
//            err3 += "~Email field is empty~";
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                str.append("The email is not in the right format: x@x.x");
//                err3 += "The email is not in the right format: x@x.x";
            }
        }
        return str;
    }

    /**
     * Validates the vendor's phone number.
     *
     * @param phone the phone number to be validated
     * @return an error message if the phone number is invalid; otherwise, an empty string
     */
    public StringBuilder phoneValidation(String phone) {
//        String err4 = "";
        str = new StringBuilder();
        String regex = "^\\(?\\d{3}\\)?[-]?\\d{3}[-]?\\d{4}$";
        if (phone == null) {
            str.append("Phone field is null");
//            err4 += "~Phone field is null~";
        } else if (phone.isBlank()) {
            str.append("Phone field is empty");
//            err4 += "~Phone field is empty~";
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                str.append("The phone number is not in the right format: 999-999-9999 or 9999999999");
//                err4 += "~The phone number is not in the right format: 999-999-9999 or 9999999999~";
            }
        }
        return str;
    }

    /**
     * Validates the vendor's contact information.
     *
     * @param contact the contact information to be validated
     * @return an error message if the contact information is invalid; otherwise, an empty string
     */
    public StringBuilder contactValidation(Contact contact) {
//        String err5 = "";
        str = new StringBuilder();
        String emailError = String.valueOf(emailValidation(contact.getEmail()));
        String phoneError = String.valueOf(phoneValidation(contact.getPhone()));
        boolean isEmailProvided = contact.getEmail() != null && !contact.getEmail().isBlank();
        boolean isPhoneProvided = contact.getPhone() != null && !contact.getPhone().isBlank();
        if (!emailError.isBlank() && !phoneError.isBlank()) {
            str.append("Either a valid email or phone number must be provided");
            str.append(contact.getEmail());
            str.append(contact.getPhone());
        } else {
            if (isEmailProvided && !emailError.isBlank()) {str.append(emailError);
            }
            if (isPhoneProvided && !phoneError.isBlank()) {str.append(phoneError);
            }
        }
        if (contact.getContactName() == null) {
            str.append("Contact name field is null");
//            err5 += "~Contact name field is null~";
        } else if (contact.getContactName().isBlank()) {
            str.append("Contact name field is empty");
//            err5 += "~Contact name field is empty~";
        } else if (!contact.getContactName().matches("^[a-zA-Z'-]+\\s[a-zA-Z'-]+$")) {
            str.append("Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes are allowed");
//            err5 += "~Please add Contact name as First name and Last name separated by a space. Only alphabetic characters, hyphens or apostrophes allowed~";
        }
        if (contact.getTitleOrRole() == null) {
            str.append("Title/Role field is null");
//            err5 += "~Title/Role field is null~";
        } else if (contact.getTitleOrRole().isBlank()) {
            str.append("Title/Role field is empty");
//            err5 += "~Title/role field is empty~";
        }
        return str;
    }

    /**
     * Validates the vendor's details.
     *
     * @param vendor the vendor to be validated
     * @return a concatenation of error messages for invalid fields; otherwise, an empty string
     */
    public String validateVendor(Vendor vendor) {
            return nameValidation(vendor.getName()).toString() + addressValidation(vendor.getAddress()) + contactValidation(vendor.getContact());
        }
}