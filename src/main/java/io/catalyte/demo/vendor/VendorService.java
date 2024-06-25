package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Vendor;

import java.util.List;

public interface VendorService {

    // CRUD methods:

    List<Vendor> getVendors();

    Vendor getVendorById(int id);

    List<Vendor> getVendorByName(String name);

    Vendor createVendor(Vendor vendorToCreate);

    Vendor editVendor(Vendor vendorToEdit, int id);

    void deleteVendorById(int id);
}
