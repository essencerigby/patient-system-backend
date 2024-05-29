package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Vendor entities in the database
 * Extends JpaRepository to provide CRUD operations
 */

@Repository
public interface VendorRepository extends
        JpaRepository<Vendor, Integer> {

}
