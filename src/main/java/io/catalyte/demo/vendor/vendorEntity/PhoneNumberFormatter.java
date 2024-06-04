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

        // Format the phone number as xxx-xxx-xxxx
        return digits.replaceAll(
                "(\\d{3})(\\d{3})(\\d{4})",
                "$1-$2-$3");
    }

}
