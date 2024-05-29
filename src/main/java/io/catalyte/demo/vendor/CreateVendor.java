package io.catalyte.demo.vendor;

import io.catalyte.demo.vendor.vendorEntity.Contact;
import io.catalyte.demo.vendor.vendorEntity.PhoneNumberFormatter;
import io.catalyte.demo.vendor.vendorEntity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CreateVendor {

   private final VendorRepository createVendorRepository;

   @Autowired
   public CreateVendor(VendorRepository createVendorRepository, PhoneNumberFormatter phoneNumberFormatter) {
       this.createVendorRepository = createVendorRepository; // Dependency injection
   }

   public Vendor createVendor(Vendor vendorToCreate) {

       // Setting timestamp at creation
       vendorToCreate.setCreatedAt(getFormattedTimeStamp(LocalDateTime.now()));

       // Setting timestamp at most recent update
       vendorToCreate.setUpdatedAt(getFormattedTimeStamp(LocalDateTime.now()));

       // Phone formatting to ensure phone number string can be saved as xxx-xxx-xxxx
       Contact contactPhoneNumberToFormat = vendorToCreate.getContact();
       try {
           String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(contactPhoneNumberToFormat.getPhone());
           contactPhoneNumberToFormat.setPhone(formattedPhoneNumber);
       } catch (
               IllegalArgumentException e) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   e.getMessage());
       }

       try {
           return createVendorRepository.save(
                   vendorToCreate);
       } catch (Exception e) {
           throw new ResponseStatusException(
                   HttpStatus.INTERNAL_SERVER_ERROR,
                   "Unexpected error occurred");
       }

   }

    public String getFormattedTimeStamp(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return timestamp.format(formatter);
    }

}
