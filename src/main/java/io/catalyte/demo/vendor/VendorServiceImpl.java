package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.PhoneNumberFormatter;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import io.catalyte.demo.vendor.vendorEntity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Vendor objects.
 */
@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    NameUniqueValidator nameValidator = new NameUniqueValidator();
    VendorValidation validator = new VendorValidation();

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /**
     * Retrieves all vendors from the repository.
     *
     * @return a list of all vendors
     */
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    /**
     * Retrieves a vendor by its ID.
     * Throws a ResponseStatusException if the vendor is not found.
     * @param id the ID of the vendor to retrieve
     * @return the vendor with the specified ID
     */
    public Vendor getVendorById(int id) {
        try {
            return vendorRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found.");
        }
    }

    /**
     * Retrieves a vendor by its name.
     * Throws a ResponseStatusException if the vendor is not found.
     * @param name the name of the vendor to retrieve
     * @return vendor with the specified name; case-insensitive
     */
    @Override
    public List<Vendor> getVendorByName(String name) {
        if (name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name value is empty");
        }

        List<Vendor> vendors = vendorRepository.findByNameIgnoreCase(name);

        if (!vendors.isEmpty()) {
            return vendorRepository.findByNameIgnoreCase(name);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    /**
     * Creates a new vendor in the repository
     * @param vendorToCreate - name of the vendor, address, contact (point of contact)
     * @return the created vendor
     */
    public Vendor createVendor(Vendor vendorToCreate) {
        // Setting timestamp at creation
        vendorToCreate.setCreatedAt(getFormattedTimeStamp(LocalDateTime.now()));

        // Setting timestamp at most recent update
        vendorToCreate.setUpdatedAt(getFormattedTimeStamp(LocalDateTime.now()));

        // Phone formatting to ensure phone number string can be saved as xxx-xxx-xxxx
        Contact contactPhoneNumberToFormat = vendorToCreate.getContact();
        String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(contactPhoneNumberToFormat.getPhone());
        contactPhoneNumberToFormat.setPhone(formattedPhoneNumber);

        // Validating the vendor information
        String[] errorArray = validator.validateVendor(vendorToCreate);
        String errors = String.join(", ", errorArray); // Join the array elements into a single string
        if (!errors.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
        }
        return vendorRepository.save(vendorToCreate);
    }

    /**
     * Edits an existing vendor.
     *
     * @param vendorToEdit the vendor with updated details
     * @param id the ID of the vendor to update
     * @return the updated vendor
     */
    public Vendor editVendor(Vendor vendorToEdit, int id) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"));

        if (vendorRepository.findById(id).isPresent()) {
            vendorToEdit.setId(id);
            vendorToEdit.setCreatedAt(getVendorById(id).getCreatedAt());
            vendorToEdit.setUpdatedAt(timeStamp);

            // Phone formatting to ensure phone number string can be saved as xxx-xxx-xxxx
            Contact contactPhoneNumberToFormat = vendorToEdit.getContact();
            String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(contactPhoneNumberToFormat.getPhone());
            contactPhoneNumberToFormat.setPhone(formattedPhoneNumber);

            String[] errorArray = validator.validateVendor(vendorToEdit);
            String errors = String.join(", ", errorArray); // Join the array elements into a single string
            if (!errors.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
            }
            vendorRepository.save(vendorToEdit);
            return vendorToEdit;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Vendor was not found");
    }

    /**
     * Deletes a vendor by its ID
     */
    public void deleteVendor(int id) {
        // PLACEHOLDER
    }

    public String getFormattedTimeStamp(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return timestamp.format(formatter);
    }
}

