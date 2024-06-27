package io.catalyte.demo.ingredient;

import io.catalyte.demo.vendor.vendorEntity.Vendor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Formats ingredient amount to be stored with two decimal places
 */
public class IngredientValidator {
    public String formatAmount (String amount) {
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * Validates an ingredient's name.
     *
     * @param name the name to be validated
     * @return a list of error messages if the name is invalid; otherwise, an empty list
     */
    public List<String> nameValidation(String name) {
        List<String> errors = new ArrayList<>();
        if (name == null) {
            errors.add("Name field is null");
        } else if (name.isBlank()) {
            errors.add("Name field is empty");
        } else if (name.length() >= 50) {
            errors.add("Please enter an ingredient name shorter than 50 characters");
        }
        return errors;
    }

    /**
     * Validates an ingredient's details.
     *
     * @param ingredient the vendor to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validateIngredient(Ingredient ingredient) {
        List<String> errors = new ArrayList<>();
        errors.addAll(nameValidation(ingredient.getName()));

        return errors.toArray(new String[0]);
    }
}
