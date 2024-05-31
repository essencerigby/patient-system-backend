package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameUniqueValidatorTest {
    NameUniqueValidator validator = new NameUniqueValidator();

    @Test
    public void testIsNameUnique_NameNotInList_ShouldReturnEmptyString() {
        List<Vendor> vendorsList = new ArrayList<>();
        vendorsList.add(new Vendor("ExistingVendor", new Address(), new Contact()));
        String result = validator.isNameUnique("NewVendor", vendorsList);
        assertEquals("", result);
    }

    @Test
    public void testIsNameUnique_NameInList_ShouldReturnErrorMessage() {
        List<Vendor> vendorsList = new ArrayList<>();
        vendorsList.add(new Vendor("ExistingVendor", new Address(), new Contact()));
        String result = validator.isNameUnique("ExistingVendor", vendorsList);
        assertEquals("Vendor with this name already exists", result);
    }
}
