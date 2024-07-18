package io.catalyte.demo.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        PatientUniqueValidator uniqueValidator = new PatientUniqueValidator();

        String isUniqueValidationMessage = uniqueValidator.areSSNAndEmailUnique(patientToCreate.getSsn(), patientToCreate.getEmail(), getPatients());
        if (!isUniqueValidationMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, isUniqueValidationMessage);
        }

        List<String> errors = List.of(validator.validatePatient(patientToCreate));
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }

        return patientRepository.save(patientToCreate);
    }

    /**
     * Edits an existing patient.
     *Throws a ResponseStatusException if values become invalid,
       ssn/email belongs to different patient, or the patient is not found
     * @param patientToUpdate the patient with updated details
     * @param id the ID of the patient to update
     * @return the updated patient
     */
    public Patient editPatient(Patient patientToUpdate, int id) {
        PatientValidation validator = new PatientValidation();
        PatientUniqueValidator uniqueValidator = new PatientUniqueValidator();

        Optional<Patient> existingPatientOpt = patientRepository.findById(id);
        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();
            patientToUpdate.setId(id);

            String isUniqueValidationMessage = uniqueValidator.areSSNAndEmailUniqueForUpdate(
                    patientToUpdate.getSsn(), patientToUpdate.getEmail(), patientToUpdate.getId(), getPatients());
            if (!isUniqueValidationMessage.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, isUniqueValidationMessage);
            }

            List<String> errors = List.of(validator.validatePatient(patientToUpdate));
            if (!errors.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
            }

            return patientRepository.save(patientToUpdate);
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

