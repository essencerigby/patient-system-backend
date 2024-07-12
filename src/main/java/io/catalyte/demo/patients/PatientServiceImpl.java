package io.catalyte.demo.patients;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Patient objects.
 */
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieves all patients from the repository.
     *
     * @return a list of all patients
     */
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by its ID.
     * Throws a ResponseStatusException if the patient is not found.
     * @param id the ID of the patient to retrieve
     * @return the patient with the specified ID
     */
    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found."));
    }

    /**
     * Creates a new patient in the repository
     *
     * @param patientToCreate - first and last name, ssn, email, address, gender, age, height, weight, insurance
     * @return the created patient
     */
    @Override
    public Patient createPatient(Patient patientToCreate) {
        PatientValidation validator = new PatientValidation();
        PatientUniqueValidator titleValidator = new PatientUniqueValidator();

        List<String> errors = List.of(validator.validateMovie(patientToCreate));
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }

        String titleValidationMessage = titleValidator.isTitleUnique(patientToCreate.getSsn(), getPatients());
        if (!titleValidationMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, titleValidationMessage);
        }

        return patientRepository.save(patientToCreate);
    }

    /**
     * Edits an existing patient.
     *
     * @param patientToEdit the patient with updated details
     * @param id the ID of the patient to update
     * @return the updated patient
     */
    public Patient editPatient(Patient patientToEdit, int id) {
        PatientValidation validator = new PatientValidation();

        if (patientRepository.findById(id).isPresent()) {
            patientToEdit.setId(id);

            List<String> errors = List.of(validator.validateMovie(patientToEdit));
            if (!errors.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
            }
            return patientRepository.save(patientToEdit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient was not found");
        }
    }

    /**
     * Deletes a patient by its ID
     * Throws a ResponseStatusException if the patient is not found.
     * @param id the ID of the patient to retrieve
     */
    public void deletePatientById(int id) {
        if (patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with matching id could not be found.");
        }
    }

}

