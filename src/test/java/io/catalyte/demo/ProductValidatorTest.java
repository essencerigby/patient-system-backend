package io.catalyte.demo;

import io.catalyte.demo.products.Product;
import io.catalyte.demo.products.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductValidatorTest {
    @Mock
    private ProductValidator productValidator = new ProductValidator();

    Product testProduct;

    @BeforeEach
    public void setUp() {
        List<String> sampleIngredientList = Arrays.asList(
                "Ingredient 1",
                "Ingredient 2"
        );

        List<String> sampleAllergenList = Arrays.asList(
                "dairy",
                "nuts"
        );

        testProduct = new Product(1, true, "Sample Description",
                "TestName", 5, sampleIngredientList,
                "Drink", "Soda", "5.0", sampleAllergenList, "5.0", "5.0");
    }

    @Test
    public void validateProductDescription_withNullDescription_returnsError() {
        testProduct.setDescription(null);
        String err = productValidator.validateProductDescription(testProduct);
        assertEquals("-Description is null.", err, "Description is not null.");
    }

    @Test
    public void validateProductDescription_withEmptyDescription_returnsError() {
        testProduct.setDescription("");
        String err = productValidator.validateProductDescription(testProduct);
        assertEquals("-Description is empty.", err, "Description has value.");
    }

    @Test
    public void validateProductDescription_descriptionTooLong_returnsError() {
        testProduct.setDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        String err = productValidator.validateProductDescription(testProduct);
        assertEquals("-Description must be less than 100 characters.", err, "Description is less than 100 characters.");
    }

    @Test
    public void validateProductDescription_withValidDescription_returnsEmptyString() {
        String err = productValidator.validateProductDescription(testProduct);
        assertEquals("", err, "Description is invalid.");
    }

    @Test
    public void validateProductVendorID_returnsNull() {
        assertNull(productValidator.validateProductVendorID(testProduct));
    }

    @Test
    public void validateProductIngredientsList_withNullIngredientsList_returnsError() {
        testProduct.setIngredientsList(null);
        String err = productValidator.validateProductIngredientsList(testProduct);
        assertEquals("-IngredientsList is null.", err, "IngredientsList is not null.");
    }

    @Test
    public void validateProductIngredientsList_withEmptyIngredientsList_returnsError() {
        List<String> emptyList = new ArrayList<>();
        testProduct.setIngredientsList(emptyList);
        String err = productValidator.validateProductIngredientsList(testProduct);
        assertEquals("-IngredientsList is empty.", err, "IngredientList is not empty.");
    }

    @Test
    public void validateProductIngredientsList_withValidIngredientsList_returnsEmptyString() {
        String err = productValidator.validateProductIngredientsList(testProduct);
        assertEquals("", err, "IngredientList is invalid.");
    }

    @Test
    public void validateProductClassification_withNullClassification_returnsError() {
        testProduct.setClassification(null);
        String err = productValidator.validateProductClassification(testProduct);
        assertEquals("-Classification is null.", err, "Classification is not null.");
    }

    @Test
    public void validateProductClassification_withEmptyClassification_returnsError() {
        testProduct.setClassification("");
        String err = productValidator.validateProductClassification(testProduct);
        assertEquals("-Classification is empty.", err, "Classification is not empty.");
    }

    @Test
    public void validateProductClassification_asInvalidClassification_returnsError() {
        testProduct.setClassification("Invalid Classification");
        String err = productValidator.validateProductClassification(testProduct);
        assertEquals("-Classification must be Drink or Baked Good.", err, "Classification is valid.");
    }

    @Test
    public void validateProductClassification_asBakedGood_returnsEmptyString () {
        testProduct.setClassification("Baked Good");
        String err = productValidator.validateProductClassification(testProduct);
        assertEquals("", err, "Classification does not equal Baked Good.");
    }

    @Test
    public void validateProductClassification_asDrink_returnsEmptyString () {
        String err = productValidator.validateProductClassification(testProduct);
        assertEquals("", err, "Classification does not equal Baked Good.");
    }

    @Test
    public void validateProductType_withNullType_returnsError() {
        testProduct.setType(null);
        String err = productValidator.validateProductType(testProduct);
        assertEquals("-Type is null.", err, "Type is not null.");
    }

    @Test
    public void validateProductType_withEmptyType_returnsError() {
        testProduct.setType("");
        String err = productValidator.validateProductType(testProduct);
        assertEquals("-Type is empty.", err, "Type is not empty.");
    }

    @Test
    public void validateProductType_withInvalidType_returnsError() {
        testProduct.setType("Invalid Type");
        String err = productValidator.validateProductType(testProduct);
        assertEquals("-Type must be Coffee, Tea, or Soda.", err, "Type is valid.");
    }

    @Test
    public void validateProductType_withValidType_returnsEmptyString() {
        String err = productValidator.validateProductType(testProduct);
        assertEquals("", err, "Type is valid.");

        testProduct.setType("CoffeE");
        err = productValidator.validateProductType(testProduct);
        assertEquals("", err, "Type is valid.");

        testProduct.setType("tEa");
        err = productValidator.validateProductType(testProduct);
        assertEquals("", err, "Type is valid.");
    }

    @Test
    public void validateProductCost_withNullCost_returnsError() {
        testProduct.setCost(null);
        String err = productValidator.validateProductCost(testProduct);
        assertEquals("-Cost is null.", err, "Cost is not null.");
    }

    @Test
    public void validateProductCost_withEmptyCost_returnsError() {
        testProduct.setCost("");
        String err = productValidator.validateProductCost(testProduct);
        assertEquals("-Cost is empty.", err, "Cost is not empty.");
    }

    @Test
    public void validateProductCost_withInvalidCost_returnsError() {
        testProduct.setCost("10.NotValid");
        String err = productValidator.validateProductCost(testProduct);
        assertEquals("-Cost must be a number.", err, "Cost is valid.");
    }

    @Test
    public void validateProductCost_withValidCost_returnsEmptyString() {
        String err = productValidator.validateProductCost(testProduct);
        assertEquals("", err, "Cost is not valid.");
    }

    @Test
    public void validateProductMarkup_withNullMarkup_returnsError() {
        testProduct.setMarkup(null);
        String err = productValidator.validateProductMarkup(testProduct);
        assertEquals("-Markup is null.", err, "Markup is not null.");
    }

    @Test
    public void validateProductMarkup_withEmptyMarkup_returnsError() {
        testProduct.setMarkup("");
        String err = productValidator.validateProductMarkup(testProduct);
        assertEquals("-Markup is empty.", err, "Markup is not empty.");
    }

    @Test
    public void validateProductMarkup_withInvalidMarkup_returnsError() {
        testProduct.setMarkup("10.NotValid");
        String err = productValidator.validateProductMarkup(testProduct);
        assertEquals("-Markup must be a number.", err, "Markup is valid.");
    }

    @Test
    public void validateProductMarkup_withValidMarkup_returnsEmptyString() {
        String err = productValidator.validateProductMarkup(testProduct);
        assertEquals("", err, "Markup is not valid.");
    }
}
