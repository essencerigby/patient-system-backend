package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Vendor;
import java.util.List;

/**
 * Provides validation to check if a vendor name already exists within a list of vendors.
 */
public class NameUniqueValidator {

    /**
     * Checks if the given name is unique within the provided list of vendors.
     *
     * @param name the name to be checked for uniqueness
     * @param vendorsList the list of vendors to check against
     * @return a message indicating the result of the validation. If the name is unique, an empty string is returned.
     *         If a vendor with the given name already exists, a message indicating this is returned.
     */
    public String isNameUnique (String name, List<Vendor> vendorsList) {

        for (Vendor vendor : vendorsList) {
                if (vendor.getName().equals(name)) {
                    return "Vendor with this name already exists";
                }
            }
        return "";
    }
}
