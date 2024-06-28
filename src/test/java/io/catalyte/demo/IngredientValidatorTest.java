package io.catalyte.demo;

import io.catalyte.demo.ingredient.Ingredient;
import io.catalyte.demo.ingredient.IngredientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientValidatorTest {
    @Mock
    private IngredientValidator validator = new IngredientValidator();
    Ingredient testIngredient;

    @BeforeEach
    public void setUp() {
        List<String> sampleAllergenList = Arrays.asList(
                "Dairy",
                "Nuts"
        );

        testIngredient = new Ingredient(1,true,"Ingredient",
                BigDecimal.valueOf(1.25),BigDecimal.valueOf(15.25),
                "lb",sampleAllergenList);
    }

    @Test
    public void formatBigDecimal_withValidAmount_formatsCorrectly() {
        BigDecimal formattedAmount = validator.formatBigDecimal(testIngredient.getAmount());
        assertEquals(new BigDecimal("15.25"), formattedAmount, "Formatted amount is incorrect.");
    }

    @Test
    public void amountValidation_withNullAmount_returnsError() {
        String err = validator.amountValidation(null);
        assertEquals("Amount is null. Please add an amount greater than 0.", err, "Null amount error is incorrect.");
    }

    @Test
    public void amountValidation_withZeroAmount_returnsError() {
        BigDecimal amount = BigDecimal.ZERO;
        String err = validator.amountValidation(amount);
        assertEquals("Please add an amount greater than 0.", err, "Zero amount error is incorrect.");
    }

    @Test
    public void amountValidation_withValidAmount_returnsNoError() {
        BigDecimal amount = new BigDecimal("1.00");
        String err = validator.amountValidation(amount);
        assertEquals("", err, "Valid amount error is incorrect.");
    }

    @Test
    public void nameValidation_withNullName_returnsError() {
        String name = null;
        String err = validator.nameValidation(name);
        assertEquals("Name field is null", err, "Null name error is incorrect.");
    }

    @Test
    public void nameValidation_withEmptyName_returnsError() {
        String name = "";
        String err = validator.nameValidation(name);
        assertEquals("Name field is empty", err, "Empty name error is incorrect.");
    }

    @Test
    public void nameValidation_withLongName_returnsError() {
        String name = "This is a very long name that exceeds the fifty characters limit";
        String err = validator.nameValidation(name);
        assertEquals("Please enter an ingredient name shorter than 50 characters", err, "Long name error is incorrect.");
    }

    @Test
    public void nameValidation_withValidName_returnsNoError() {
        String name = "Sugar";
        String err = validator.nameValidation(name);
        assertEquals("", err, "Valid name error is incorrect.");
    }

    @Test
    public void activeOrInactiveValidation_withNullStatus_returnsError() {
        Boolean active = null;
        String err = validator.activeOrInactiveValidation(active);
        assertEquals("Null value not allowed. Please type 'true' for active OR 'false' for inactive.", err, "Null status error is incorrect.");
    }

    @Test
    public void activeOrInactiveValidation_withValidStatus_returnsNoError() {
        Boolean active = true;
        String err = validator.activeOrInactiveValidation(active);
        assertEquals("", err, "Valid status error is incorrect.");
    }

    @Test
    public void purchasingCostValidation_withNullCost_returnsError() {
        BigDecimal cost = null;
        String err = validator.purchasingCostValidation(cost);
        assertEquals("The cost is null. Input a valid number", err, "Null cost error is incorrect.");
    }

    @Test
    public void purchasingCostValidation_withZeroCost_returnsError() {
        BigDecimal cost = BigDecimal.ZERO;
        String err = validator.purchasingCostValidation(cost);
        assertEquals("The cost must be greater than 0", err, "Zero cost error is incorrect.");
    }

    @Test
    public void purchasingCostValidation_withValidCost_returnsNoError() {
        BigDecimal cost = new BigDecimal("1.00");
        String err = validator.purchasingCostValidation(cost);
        assertEquals("", err, "Valid cost error is incorrect.");
    }

    @Test
    public void allergenListValidation_withNullAllergens_returnsError() {
        String err = validator.allergenListValidation(null);
        assertEquals("Null values are not allowed. Please choose at least one allergen, if applicable: Dairy, Soy, Gluten, Nuts.", err, "Null allergens error is incorrect.");
    }

    @Test
    public void allergenListValidation_withInvalidAllergen_returnsError() {
        List<String> allergens = Arrays.asList("Pollen");
        String err = validator.allergenListValidation(allergens);
        assertEquals("If this ingredient has an allergen, it must be one or more of the following: Dairy, Soy, Gluten, or Nuts.", err, "Invalid allergen error is incorrect.");
    }

    @Test
    public void allergenListValidation_withEmptyAllergenList_returnsNoError() {
        List<String> allergens = Arrays.asList();
        String result = validator.allergenListValidation(allergens);
        assertEquals("", result, "Valid allergens error is incorrect.");
    }

    @Test
    public void allergenListValidation_withValidAllergens_returnsNoError() {
        List<String> allergens = testIngredient.getAllergens();
        String result = validator.allergenListValidation(allergens);
        assertEquals("", result, "Valid allergens error is incorrect.");
    }
}
