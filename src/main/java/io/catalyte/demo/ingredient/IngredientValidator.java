package io.catalyte.demo.ingredient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Formats ingredient amount to be stored with two decimal places
 */
public class IngredientValidator {
    public String formatAmount(String amount) {
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        DecimalFormat df = new DecimalFormat("0.00");  // Pattern to ensure two decimal places
        df.setRoundingMode(RoundingMode.HALF_UP);       // Optional rounding mode setting

        return df.format(bd);

    }

    /**
     * Validates the amount of an ingredient.
     *
     * @param amount the ingredient amount to be validated
     * @return a list of error messages for null, empty, zero, or non-numeric values
     */
    public List<String> amountValidation(String amount) {
        List<String> errors = new ArrayList<>();
        if (amount == null) {
            errors.add("Amount is null. Please add a number greater than 0.");
        } else if (amount.isEmpty()) {
            errors.add("Amount is empty. Please add a number greater than 0.");
        } else {
            try {
                BigDecimal bd = new BigDecimal(amount);
                if (bd.compareTo(BigDecimal.ZERO) == 0) {
                    errors.add("Amount must be a non-zero number, with up to 2 decimal places.");
                } else {
                    String formattedAmount = formatAmount(amount);
                    Ingredient ingredient = new Ingredient();
                    ingredient.setAmount(formattedAmount);
                }
            } catch (NumberFormatException e) {
                errors.add("Amount must be a non-zero number, with up to 2 decimal places.");
            }
        }
        return errors;
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
     * Validates an ingredient's name.
     *
     * @param active the ingredient status to be validated
     * @return an error message if the status is null; only true or false are allowed
     */
    public List<String> activeOrInactiveValidation(Boolean active) {
        List<String> errors = new ArrayList<>();
        if (active == null) {
            errors.add("Null value not allowed. Please type 'true' for active OR 'false' for inactive.");
        }
        return errors;
    }

    public List<String> purchasingCostValidation(BigDecimal purchasingCost) {
        List<String> errors = new ArrayList<>();
        if(purchasingCost == null) {
            errors.add("The cost is null. Input a valid number");
        } else if (purchasingCost.compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("The cost must be greater than 0");
        }
        return errors;
    }

    /**
     * Validates an ingredient's details.
     *
     * @param ingredient the ingredient to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validateIngredient(Ingredient ingredient) {
        List<String> errors = new ArrayList<>();
        errors.addAll(nameValidation(ingredient.getName()));
        errors.addAll(amountValidation(ingredient.getAmount()));
        errors.addAll(activeOrInactiveValidation(ingredient.getActive()));
        errors.addAll(purchasingCostValidation(ingredient.getPurchasingCost()));

        return errors.toArray(new String[0]);
    }
}
