package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Vendor objects.
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final CreateVendor createVendor;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, CreateVendor createVendor) {
        this.vendorRepository = vendorRepository;
        this.createVendor = createVendor;
    }
    /**
     * Retrieves all vendors from the repository.
     *
     * @return a list of all vendors
     */
    public List<Vendor> getVendors() {
        return vendorRepository.findAll(); // PLACEHOLDER
    }

    /**
     * Retrieves a vendor by its ID.
     * Throws a ResponseStatusException if the vendor is not found.
     * @param id the ID of the vendor to retrieve
     * @return the vendor with the specified ID
     */
    public Vendor getVendorById(int id) {
        return null; // PLACEHOLDER
    }

    /**
     * Creates a new vendor in the repository
     * @param vendorToCreate - name of the vendor, address, contact (point of contact)
     * @return the created vendor
     */
    public Vendor createVendor(Vendor vendorToCreate) {
        return createVendor.createVendor(vendorToCreate);
    }

    /**
     * Edits an existing vendor
     */
    public Vendor editVendor(Vendor vendorToEdit, int id) {
        return null; // PLACEHOLDER
    }

    /**
     * Deletes a vendor by its ID
     */
    public void deleteVendor(int id) {
        // PLACEHOLDER

    }
}

