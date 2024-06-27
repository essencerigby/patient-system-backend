package io.catalyte.demo.ingredient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Formats ingredient amount to be stored with two decimal places
 */
public class IngredientValidator {
    public void formatAmount (Ingredient ingredient) {
        BigDecimal bd = new BigDecimal(ingredient.getAmount());
        ingredient.setAmount(String.valueOf(bd.setScale(2, RoundingMode.HALF_UP)));
    }

    public List<String> amountValidation(String amount) {
        List<String> errors = new ArrayList<>();
        if (amount == null) {
            errors.add("Amount is null. Please add a non-zero number value.");
        } else if (amount.isEmpty()) {
            errors.add("Amount is empty. Please add a non-zero number value.");
        } else {
            try {
                Ingredient ingredient = new Ingredient();
                ingredient.setAmount(amount);
                formatAmount(ingredient);

                if (new BigDecimal(ingredient.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
                    errors.add("Amount must be a non-zero number, with up to 2 decimal places.");
                }
            } catch (NumberFormatException e) {
                errors.add("Amount must be a number, with up to 2 decimal places.");
            } catch (ArithmeticException e) {
                errors.add("Amount format is invalid.");
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

    public List<String> activeOrInactiveValidation(Boolean active) {
        List<String> errors = new ArrayList<>();
        // ADD NULL CHECK HERE
        if(active.toString().isEmpty()) {
            errors.add("Please type 'true' for active OR 'false' for inactive.");
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

        return errors.toArray(new String[0]);
    }
}
