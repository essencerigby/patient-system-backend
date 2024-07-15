package io.catalyte.demo.patients;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing Patient entities in the database
 * Extends JpaRepository to provide CRUD operations
 */

@Repository
public interface PatientRepository extends
        JpaRepository<Patient, Integer> {

}
