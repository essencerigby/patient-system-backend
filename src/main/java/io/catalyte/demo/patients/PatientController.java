package io.catalyte.demo.patients;

import java.util.List;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    /**
     * A controller class to map CRUD functions from PatientService to RESTful endpoints
     * Autowired to PatientServiceImpl (service class)
     * */

    private final PatientService patientService;

    /**
     * Injecting PatientService implementation
     * @param patientService - the service for performing CRUD methods on Patient instances
     * */
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves all patients
     * @return list of all patients
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    /**
     * Retrieves a patients of specified ID
     * @param id is ID of patients
     * @return patients with specified ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    /**
     * Creates a patients so long as properties are valid
     * @param patientToCreate - the patients whose creation is requested
     * @return created patients
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patientToCreate) {
        return patientService.createPatient(patientToCreate);
    }

    /**
     * Edits a patients with specified ID
     * @param id is ID of patients
     * @return edited patients and its ID
     * */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@RequestBody Patient patientToEdit, @PathVariable int id) {
        return patientService.editPatient(patientToEdit, id);
    }

    /**
     * Deletes a patients with specified ID
     * @param id is ID of patients
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatientById(id);
    }
}

