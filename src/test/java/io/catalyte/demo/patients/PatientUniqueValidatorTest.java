package io.catalyte.demo.patients;

import io.catalyte.demo.patients.patientEntity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class PatientUniqueValidatorTest {
    PatientUniqueValidator validator = new PatientUniqueValidator();

    @Test
    public void testIsTitleUnique_TitleNotInList_ShouldReturnEmptyString() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("ExistingVendor", "Documentary", "Joe Doe", new BigDecimal("6.50")));
        String result = validator.isTitleUnique("NewVendor", patientList);
        assertEquals("", result);
    }

    @Test
    public void testIsTitleUnique_TitleInList_ShouldReturnErrorMessage() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("ExistingVendor", "Documentary", "Joe Doe", new BigDecimal("6.50")));
        String result = validator.isTitleUnique("ExistingVendor", patientList);
        assertEquals("Patient with this name already exists", result);
    }
}
