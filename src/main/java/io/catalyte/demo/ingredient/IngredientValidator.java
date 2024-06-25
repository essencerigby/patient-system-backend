package io.catalyte.demo.ingredient;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Formats ingredient amount to be stored with two decimal places
 */
public class IngredientValidator {
    public String formatAmount (String amount) {
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }
}
