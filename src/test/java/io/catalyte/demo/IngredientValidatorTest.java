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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals("Amount is null. Please add an amount greater than 0.", err,
                "Null amount error is incorrect.");
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
        String err = validator.nameValidation(null);
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
        assertEquals("Please enter an ingredient name shorter than 50 characters",
                err, "Long name error is incorrect.");
    }

    @Test
    public void nameValidation_withValidName_returnsNoError() {
        String name = "Sugar";
        String err = validator.nameValidation(name);
        assertEquals("", err, "Valid name error is incorrect.");
    }

    @Test
    public void activeOrInactiveValidation_withNullStatus_returnsError() {
        String err = validator.activeOrInactiveValidation(null);
        assertEquals("Null value not allowed. Please type 'true' for active OR 'false' for inactive.",
                err, "Null status error is incorrect.");
    }

    @Test
    public void activeOrInactiveValidation_withValidStatus_returnsNoError() {
        Boolean active = true;
        String err = validator.activeOrInactiveValidation(active);
        assertEquals("", err, "Valid status error is incorrect.");
    }

    @Test
    public void purchasingCostValidation_withNullCost_returnsError() {
        String err = validator.purchasingCostValidation(null);
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
        assertEquals("Null values are not allowed. Please choose at least one allergen, if applicable: " +
                "Dairy, Soy, Gluten, Nuts.", err, "Null allergens error is incorrect.");
    }

    @Test
    public void allergenListValidation_withInvalidAllergen_returnsError() {
        List<String> allergens = List.of("Pollen");
        String err = validator.allergenListValidation(allergens);
        assertEquals("If this ingredient has an allergen, it must be one or more of the following: " +
                "Dairy, Soy, Gluten, or Nuts. Values are case sensitive.", err, "Invalid allergen error is incorrect.");
    }

    @Test
    public void allergenListValidation_withEmptyAllergenList_returnsNoError() {
        List<String> allergens = List.of();
        String result = validator.allergenListValidation(allergens);
        assertEquals("", result, "Valid allergens error is incorrect.");
    }

    @Test
    public void allergenListValidation_withValidAllergens_returnsNoError() {
        List<String> allergens = testIngredient.getAllergens();
        String result = validator.allergenListValidation(allergens);
        assertEquals("", result, "Valid allergens error is incorrect.");
    }

    @Test
    public void unitOfMeasurementValidation_withInvalidMeasurement_returnsError() {
        String measurement = "ounces";
        String err = validator.unitOfMeasurementValidation(measurement);
        assertEquals("Invalid unit of measure. Please use one of the following: " +
                "oz, ml, kg, lb, tsp, tbsp, cups.", err, "Invalid measurement error is incorrect.");
    }

    @Test
    public void unitOfMeasurementValidation_withNullMeasurement_returnsError() {
        String err = validator.unitOfMeasurementValidation(null);
        assertEquals("Null values are not allowed. Please use one of the following: " +
                "oz, ml, kg, lb, tsp, tbsp, cups.", err, "Invalid measurement error is incorrect.");
    }

    @Test
    public void unitOfMeasurementValidation_withEmptyMeasurement_returnsError() {
        String measurement = "";
        String err = validator.unitOfMeasurementValidation(measurement);
        assertEquals("A unit of measure is required. Please use one of the following: " +
                "oz, ml, kg, lb, tsp, tbsp, cups.", err, "Invalid measurement error is incorrect.");
    }

    @Test
    public void unitOfMeasurementValidation_withValidMeasurement_noErrors() {
        String measurement = "OZ";
        String result = validator.unitOfMeasurementValidation(measurement);
        assertEquals("", result, "Invalid measurement error is incorrect.");
    }

    @Test
    public void testValidateIngredient_AllValid() {
        String[] errors = validator.validateIngredient(testIngredient);

        assertEquals(1, errors.length, "Expected no validation errors");
    }

    @Test
    public void testValidateIngredient_InvalidName() {
        testIngredient.setName("");

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for name");
        assertTrue(Arrays.asList(errors).contains("Name field is empty"), "Expected 'Name field is empty' error");
    }

    @Test
    public void testValidateIngredient_InvalidAmount() {
        testIngredient.setAmount(BigDecimal.valueOf(0));

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for amount");
        assertTrue(Arrays.asList(errors).contains("Please add an amount greater than 0."), "Expected 'Please add an amount greater than 0.' error");
    }

    @Test
    public void testValidateIngredient_InvalidActiveStatus() {
        testIngredient.setActive(null);

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for active status");
        assertTrue(Arrays.asList(errors).contains("Null value not allowed. Please type 'true' for active OR 'false' for inactive."), "Expected 'Null value not allowed. Please type 'true' for active OR 'false' for inactive.' error");
    }

    @Test
    public void testValidateIngredient_InvalidPurchasingCost() {
        testIngredient.setPurchasingCost(BigDecimal.valueOf(0));

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for purchasing cost");
        assertTrue(Arrays.asList(errors).contains("The cost must be greater than 0"), "Expected 'The cost must be greater than 0' error");
    }

    @Test
    public void testValidateIngredient_InvalidAllergens() {
        List<String> invalidAllergens = List.of("Melons");
        testIngredient.setAllergens(invalidAllergens);

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for allergens");
        assertTrue(Arrays.asList(errors).contains("If this ingredient has an allergen, it must be one or more of the following: Dairy, Soy, Gluten, or Nuts. Values are case sensitive."), "Expected 'If this ingredient has an allergen, it must be one or more of the following: Dairy, Soy, Gluten, or Nuts. Values are case sensitive.' error");
    }

    @Test
    public void testValidateIngredient_InvalidUnitOfMeasure() {
        testIngredient.setUnitOfMeasure("");

        String[] errors = validator.validateIngredient(testIngredient);

        assertTrue(errors.length > 0, "Expected validation errors for unit of measure");
        assertTrue(Arrays.asList(errors).contains("A unit of measure is required. Please use one of the following: oz, ml, kg, lb, tsp, tbsp, cups."), "Expected 'A unit of measure is required. Please use one of the following: oz, ml, kg, lb, tsp, tbsp, cups.' error");
    }
}
