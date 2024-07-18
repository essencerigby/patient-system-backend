package io.catalyte.demo.patients;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Provides validation to check if a patient with a specific ssn or email already exists within a list of patients.
 */
@Component
public class PatientUniqueValidator {

    /**
     * Checks if the given ssn and email are unique within the provided list of patients.
     *
     * @param ssn the ssn to be checked for uniqueness
     * @param email the email to be checked for uniqueness
     * @param patientList the list of patients to check against
     * @return a message indicating the result of the validation. If both ssn and email are unique, an empty string is returned.
     * If a patient with the given ssn or email already exists, a message indicating this is returned.
     */
    public String areSSNAndEmailUnique(String ssn, String email, List<Patient> patientList) {
        boolean ssnExists = false;
        boolean emailExists = false;

        for (Patient patient : patientList) {
            if (patient.getSsn().equals(ssn)) {
                ssnExists = true;
            }
            if (patient.getEmail().equals(email)) {
                emailExists = true;
            }
            if (ssnExists && emailExists) {
                break;
            }
        }

        StringBuilder message = new StringBuilder();
        if (ssnExists) {
            message.append("Patient with this ssn already exists. ");
        }
        if (emailExists) {
            message.append("Patient with this email already exists.");
        }

        return message.toString().trim();
    }

    /**
     * Checks if the given ssn and email are unique within the provided list of patients for an update operation.
     *
     * @param ssn the ssn to be checked for uniqueness
     * @param email the email to be checked for uniqueness
     * @param id the id of the patient being updated
     * @param patientList the list of patients to check against
     * @return a message indicating the result of the validation. If both ssn and email are unique for other patients, an empty string is returned.
     * If a patient with the given ssn or email already exists but has a different ID, a message indicating this is returned.
     */
    public String areSSNAndEmailUniqueForUpdate(String ssn, String email, int id, List<Patient> patientList) {
        boolean ssnExists = false;
        boolean emailExists = false;

        for (Patient patient : patientList) {
            if (patient.getId() != id) {
                if (patient.getSsn().equals(ssn)) {
                    ssnExists = true;
                }
                if (patient.getEmail().equals(email)) {
                    emailExists = true;
                }
            }
            // If both ssnExists and emailExists are true, we can break the loop early
            if (ssnExists && emailExists) {
                break;
            }
        }

        StringBuilder message = new StringBuilder();
        if (ssnExists) {
            message.append("Patient with this ssn already exists. ");
        }
        if (emailExists) {
            message.append("Patient with this email already exists.");
        }

        return message.toString().trim();
    }
}
