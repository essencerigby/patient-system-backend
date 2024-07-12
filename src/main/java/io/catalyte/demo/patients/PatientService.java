package io.catalyte.demo.patients;

import io.catalyte.demo.patients.patientEntity.Patient;

import java.util.List;

public interface PatientService {

    // CRUD methods:

    List<Patient> getPatients();

    Patient getPatientById(int id);

    Patient createPatient(Patient movieToCreate);

    Patient editPatient(Patient movieToEdit, int id);

    void deletePatientById(int id);
}
