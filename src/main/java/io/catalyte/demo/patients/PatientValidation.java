package io.catalyte.demo.patients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.springframework.stereotype.Component;

/**
 * Provides various validation methods for validating patients details.
 */
@Component
public class PatientValidation {
    final List<String> US_states = Arrays.asList("AL", "AK", "AZ", "AR",
            "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
            "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
    final List<String> gender_values = Arrays.asList("Male", "Female", "Other");


    /**
     * Validates the patient's first name.
     *
     * @param firstName the first name of patient to be validated
     * @return a list of error messages if the first name is invalid; otherwise, an empty list
     */
    public List<String> firstNameValidation(String firstName) {
        List<String> errors = new ArrayList<>();
        if (firstName == null) {
            errors.add("First Name field is null");
        } else if (firstName.isBlank()) {
            errors.add("First Name field is empty");
        } else if (firstName.length() > 30) {
            errors.add("Please enter a first name shorter than 30 characters");
        } else if (!firstName.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("First Name contains invalid characters");
        }
        return errors;
    }

    /**
     * Validates the patient's last name.
     *
     * @param lastName the last name of patient to be validated
     * @return a list of error messages if the last name is invalid; otherwise, an empty list
     */
    public List<String> lastNameValidation(String lastName) {
        List<String> errors = new ArrayList<>();
        if (lastName == null) {
            errors.add("Last Name field is null");
        } else if (lastName.isBlank()) {
            errors.add("Last Name field is empty");
        } else if (lastName.length() > 30) {
            errors.add("Please enter a last name shorter than 30 characters");
        } else if (!lastName.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("Last Name contains invalid characters");
        }
        return errors;
    }

    /**
     * Validates the patient's social security number.
     *
     * @param ssn the ssn of patient to be validated
     * @return a list of error messages if the ssn is invalid; otherwise, an empty list
     */
    public List<String> ssnValidation(String ssn) {
        List<String> errors = new ArrayList<>();
        if  (ssn == null) {
            errors.add("SSN field is null");
        } else if (ssn.isBlank()) {
            errors.add("SSN field is empty");
        } else if (!ssn.matches("^\\d{3}-\\d{2}-\\d{4}$")) {
            errors.add("SSN is in wrong format: DDD-DD-DDDD");
        }
        return errors;
    }

    /**
     * Validates the patient's email.
     *
     * @param email the email to be validated
     * @return a list of error messages if the email is invalid; otherwise, an empty list
     */
    public List<String> emailValidation(String email) {
        List<String> errors = new ArrayList<>();
        if (email == null) {
            errors.add("Email field is null");
        } else if (email.isBlank()) {
            errors.add("Email field is empty");
        } else if (email.length() > 50) {
            errors.add("Email must be less than 50 characters");
        } else if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.([a-zA-Z]+)*$")) {
                errors.add("The email is not in the right format: x@x.x");
        }
        return errors;
    }

    /**
     * Validates the patient's street address.
     *
     * @param street the street address to be validated
     * @return a list of error messages if the street is invalid; otherwise, an empty list
     */
    public List<String> streetValidation(String street) {
        List<String> errors = new ArrayList<>();
        if (street == null) {
            errors.add("Street field is null");
        } else if (street.isBlank()) {
            errors.add("Street field is empty");
        } else if (street.length() > 30) {
            errors.add("Street must be less than 30 characters");
        } else if (!street.matches("^[A-Za-z0-9\\s\\-',.]*$")) {
            errors.add("Street contains invalid characters.");
        }
        return errors;
    }

    /**
     * Validates the patient's city field.
     *
     * @param city the city to validate
     * @return a list of error messages if the city is invalid; otherwise, an empty list
     */
    public List<String> cityValidation(String city) {
        List<String> errors = new ArrayList<>();
        if (city == null) {
            errors.add("City field is null");
        } else if (city.isBlank()) {
            errors.add("City field is empty");
        } else if (city.length() > 30) {
            errors.add("Please enter a city shorter than 30 characters");
        } else if (!city.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("City contains invalid characters");
        }
        return errors;
    }

    /**
     * Validates the patient's state.
     *
     * @param state the state to be validated
     * @return a list of error messages if the state is invalid; otherwise, an empty list
     */
    public List<String> stateValidation(String state) {
        List<String> errors = new ArrayList<>();
        if (state == null) {
            errors.add("State field is null");
        } else if (state.isBlank()) {
            errors.add("State field is empty");
        } else if (!US_states.contains(state)) {
            errors.add("State must be an US State abbreviation");
        }
        return errors;
    }


    /**
     * Validates the patient's zip code
     *
     * @param zip the zip code to be validated
     * @return a list of error messages if the zip is invalid; otherwise, an empty list
     */
    public List<String> zipValidation(String zip) {
        List<String> errors = new ArrayList<>();
        if (zip == null) {
            errors.add("Zip code field is null");
        } else if (zip.isEmpty()) {
            errors.add("Zip code field is empty");
        } else if (!zip.matches("^\\d{5}(-\\d{4})?$")) {
            errors.add("Zip must be in the format DDDDD or DDDDD-DDDD");
        }
        return errors;
    }

    /**
     * Validates the patient's age
     *
     * @param age the age to validate
     * @return a list of error messages if the age is invalid; otherwise, an empty list
     */
    public List<String> ageValidation(Short age) {
        List<String> errors = new ArrayList<>();
        if (age == null) {
            errors.add("Age field is empty");
        } else if (age < 0) {
            errors.add("Age must be a positive number");
        } else if (age > 120) {
            errors.add("Age must be between 0 and 120");
        }
        return errors;
    }

    /**
     * Validates the patient's height
     *
     * @param height the height to validate
     * @return a list of error messages if the height is invalid; otherwise, an empty list
     */
    public List<String> heightValidation(Short height) {
        List<String> errors = new ArrayList<>();
        if (height == null) {
            errors.add("Height field is empty");
        }else if (height < 0) {
            errors.add("Height must be a positive number in inches");
        } else if (height > 108) {
            errors.add("Height must be between 0 and 108, no decimals");
        }
        return errors;
    }

    /**
     * Validates the patient's weight
     *
     * @param weight the age to validate
     * @return a list of error messages if the weight is invalid; otherwise, an empty list
     */
    public List<String> weightValidation(Short weight) {
        List<String> errors = new ArrayList<>();
        if (weight == null) {
            errors.add("Weight field is empty");
        }else if (weight < 0) {
            errors.add("Weight must be a positive whole number");
        } else if (weight > 1400) {
            errors.add("Weight must be between 0 and 1400, no decimals");
        }
        return errors;
    }

    /**
     * Validates the patient's gender
     *
     * @param gender the gender to validate
     * @return a list of error messages if the gender is invalid; otherwise, an empty list
     */
    public List<String> genderValidation(String gender) {
        List<String> errors = new ArrayList<>();
        if (gender == null) {
            errors.add("Gender field is null");
        } else if (gender.isEmpty()) {
            errors.add("Gender field is empty");
        } else if (!gender_values.contains(gender)) {
            errors.add("Gender must be: Male, Female, or Other");
        }
        return errors;
    }

    /**
     * Validates the patient's insurance
     *
     * @param insurance the insurance to validate
     * @return a list of error messages if the insurance is invalid; otherwise, an empty list
     */
    public List<String> insuranceValidation(String insurance) {
        List<String> errors = new ArrayList<>();
        if (insurance == null) {
            errors.add("Insurance field is null");
        } else if (insurance.isEmpty()) {
            errors.add("Insurance field is empty");
        } else if (insurance.length() > 50) {
            errors.add("Insurance must be less than 50 characters");
        } else if (!insurance.matches("[A-Za-z\\s\\-',.]*")) {
            errors.add("Insurance contains invalid characters");
        }
        return errors;
    }

    /**
     * Validates the patient's details.
     *
     * @param patient the patient to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validatePatient(Patient patient) {
        List<String> errors = new ArrayList<>();
        errors.addAll(firstNameValidation(patient.getFirstName()));
        errors.addAll(lastNameValidation(patient.getLastName()));
        errors.addAll(ssnValidation(patient.getSsn()));
        errors.addAll(emailValidation(patient.getEmail()));
        errors.addAll(streetValidation(patient.getStreet()));
        errors.addAll(cityValidation(patient.getCity()));
        errors.addAll(stateValidation(patient.getState()));
        errors.addAll(zipValidation(patient.getZip()));
        errors.addAll(ageValidation(patient.getAge()));
        errors.addAll(heightValidation(patient.getHeight()));
        errors.addAll(weightValidation(patient.getWeight()));
        errors.addAll(genderValidation(patient.getGender()));
        errors.addAll(insuranceValidation(patient.getInsurance()));
        return errors.toArray(new String[0]);
    }
}
