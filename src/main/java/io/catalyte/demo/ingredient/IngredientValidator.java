package io.catalyte.demo.ingredient;

import java.text.DecimalFormat;

/**
 * Formats ingredient amount to be stored with two decimal places
 */
public class IngredientValidator {
    public double formatIngredientAmount(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
