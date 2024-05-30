package io.catalyte.demo.vendor.vendorEntity;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberFormatter {

    /**
     * Formats phone number string to xxx-xxx-xxxx if it is not already
     *
     * @param phoneNumber - phone number string to check for correct formatting
     * @return phoneNumber to be saved
     */

    public static String formatPhoneNumber(
            String phoneNumber) {

        // Remove dashes
        String digits = phoneNumber.replaceAll("-",
                "");

        // Check if there are 10 digits
        if (digits.length() != 10) {
            throw new IllegalArgumentException(
                    "Phone number must have 10 digits and may only include dashes.");
        }

        // Format the phone number as xxx-xxx-xxxx
        return digits.replaceAll(
                "(\\d{3})(\\d{3})(\\d{4})",
                "$1-$2-$3");
    }

}
