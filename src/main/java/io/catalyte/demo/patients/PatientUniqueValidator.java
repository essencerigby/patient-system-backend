package io.catalyte.demo.patients;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Provides validation to check if a patient with a specific ssn or email already exists within a list of patients.
 */
@Component
public class PatientUniqueValidator {

    /**
     * Checks if the given title is unique within the provided list of vendors.
     *
     * @param ssn the ssn to be checked for uniqueness
     * @param email the email to be checked for uniqueness
     * @param patientList the list of patients to check against
     * @return a message indicating the result of the validation. If the ssn and/or email is unique, an empty string is returned.
     * If a patient with the given ssn and/or email already exists, a message indicating this is returned.
     */
    public String isSsnAndEmailUnique(String ssn, String email, List<Patient> patientList) {

        for (Patient patient : patientList) {
                if (patient.getSsn().equals(ssn)) {
                    return "Patient with this ssn already exists";
                } else if (patient.getEmail().equals(email)) {
                    return "Patient with this email already exists";
                }
        }
        return "";
    }
}
