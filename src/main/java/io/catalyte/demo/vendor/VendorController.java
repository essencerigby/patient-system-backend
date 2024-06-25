package io.catalyte.demo.vendor;

import java.util.List;

import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vendors")
public class VendorController {
    /**
     * A controller class to map CRUD functions from VendorService to RESTful endpoints
     * Autowired to VendorServiceImpl (service class)
     * */

    private final VendorService vendorService;

    /**
     * Injecting VendorService implementation
     * @param vendorService - the service for performing CRUD methods on Vendor instances
     * */
    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Retrieves all vendors
     * @return list of all vendors
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vendor> getVendors() {
        return vendorService.getVendors();
    }

    /**
     * Retrieves a vendor by their name.
     *
     * @param name The name of the vendor to retrieve.
     * @return The vendor(s) with the specified name.
     */
    @GetMapping(params = "name")
    public ResponseEntity<?> getVendorByName(@RequestParam(name = "name", required = false) String name) {
        List<Vendor> vendors = vendorService.getVendorByName(name);
        return ResponseEntity.ok(vendors);
    }

    /**
     * Retrieves a vendor of specified ID
     * @param id is ID of vendor
     * @return vendor with specified ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor getVendorById(@PathVariable int id) {
        return vendorService.getVendorById(id);
    }

    /**
     * Creates a vendor so long as properties are valid
     * @param vendorToCreate - the vendor whose creation is requested
     * @return created vendor
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendorToCreate) {
        return vendorService.createVendor(vendorToCreate);
    }

    /**
     * Edits a vendor with specified ID
     * @param id is ID of vendor
     * @return edited vendor and its ID
     * */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor editVendor(@RequestBody Vendor vendorToEdit, @PathVariable int id) {
        return vendorService.editVendor(vendorToEdit, id);
    }

    /**
     * Deletes a vendor with specified ID
     * @param id is ID of vendor
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendorById(@PathVariable int id) {
        vendorService.deleteVendorById(id);
    }
}

