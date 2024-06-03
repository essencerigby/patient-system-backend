package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Address;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to demonstrate the validation of Vendor objects.
 */
public class Main {
    public static void main(String[] args) {
        List<Vendor> vendorsList = new ArrayList<>();

        Address address1 = new Address();
        address1.setStreet("Street");
        address1.setStreet2("");
        address1.setCity("Niles");
        address1.setState("IL");
        address1.setZipCode("12345");

        Contact contact1 = new Contact();
        contact1.setContactName("First Last");
        contact1.setTitleOrRole("Manager");
        contact1.setPhone("1234567891");
        contact1.setEmail("valid@ex.com");

        Vendor vendor1 = new Vendor("Name", address1, contact1);
        vendor1.setCreatedAt("05-24-2024 11:19");

        Address address2 = new Address();
        Contact contact2 = new Contact();
        address2.setStreet("");
        address2.setStreet2("ap 3");
        address2.setCity("Niles");
        address2.setState("NV");
        address2.setZipCode("12348");

        contact2.setContactName("John Smith");
        contact2.setTitleOrRole("Employee");
        contact2.setPhone("123-456-7891");
        contact2.setEmail("valid@ex.com");

        Vendor vendor2 = new Vendor("Name", address2, contact2);
        vendor2.setCreatedAt("05-24-2024 11:19");

        NameUniqueValidator nameValidator = new NameUniqueValidator();
        VendorValidation validator = new VendorValidation();

        String err1 = nameValidator.isNameUnique(vendor1.getName(), vendorsList) + validator.validateVendor(vendor1);
        if (err1.isBlank()) {vendorsList.add(vendor1);
        } else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err1);
        }
        
        String err2 = nameValidator.isNameUnique(vendor2.getName(), vendorsList) + validator.validateVendor(vendor2);
        if (err2.isBlank()) {vendorsList.add(vendor2);
        } else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err2);
        }
    }
}


