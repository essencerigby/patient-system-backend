package io.catalyte.demo.vendor;

import java.util.ArrayList;
import java.util.List;

import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vendors")
public class VendorController {
    /**
     * A controller class to map CRUD functions from VendorService to RESTful endpoints
     * Autowired to VendorServiceImpl (service class)
     * */

    private final VendorService vendorService;
    private final CreateVendor createVendor;

    /**
     * Injecting VendorService implementation
     * @param vendorService - the service for performing CRUD methods on Vendor instances
     * */
    @Autowired
    public VendorController(VendorService vendorService, CreateVendor createVendor) {
        this.vendorService = vendorService;
        this.createVendor = createVendor;
    }

    private static int idCounter = 1;
    private static List<Vendor> vendors = new ArrayList<>();

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
        vendorToCreate.setId(idCounter++);
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
    public void deleteVendor(@PathVariable int id) {
        vendorService.deleteVendor(id);
    }

}

