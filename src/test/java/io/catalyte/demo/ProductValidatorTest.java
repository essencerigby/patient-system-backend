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

    Product testDrinkProduct;
    Product testBakedGoodProduct;
    Product testInvalidProduct;

    @BeforeEach
    public void setUp() {
        List<String> sampleIngredientList = Arrays.asList(
                "Ingredient 1",
                "Ingredient 2"
        );

        List<String> sampleAllergenList = Arrays.asList(
                "Dairy",
                "Nuts"
        );

        testDrinkProduct = new Product(1, true, "Sample Description",
                "DrinkName", "5", sampleIngredientList,
                "Drink", "Soda", "5.0", sampleAllergenList, "5.0", "5.0");

        testBakedGoodProduct = new Product(1, true, "Sample Description",
                "BakedGoodName", "5", sampleIngredientList,
                "Baked Good", "Soda", "5.0", sampleAllergenList, "5.0", "5.0");

        testInvalidProduct = new Product(0, true, null,
                null, null, null,
                null, null, null, null, null, null);
    }

    @Test
    public void validateProductDescription_withNullDescription_returnsError() {
        testDrinkProduct.setDescription(null);
        String err = productValidator.validateProductDescription(testDrinkProduct);
        assertEquals(" Description is null.", err, "Description is not null.");
    }

    @Test
    public void validateProductDescription_withEmptyDescription_returnsError() {
        testDrinkProduct.setDescription("");
        String err = productValidator.validateProductDescription(testDrinkProduct);
        assertEquals(" Description is empty.", err, "Description has value.");
    }

    @Test
    public void validateProductDescription_descriptionTooLong_returnsError() {
        testDrinkProduct.setDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        String err = productValidator.validateProductDescription(testDrinkProduct);
        assertEquals(" Description must be less than 100 characters.", err, "Description is less than 100 characters.");
    }

    @Test
    public void validateProductDescription_withValidDescription_returnsEmptyString() {
        String err = productValidator.validateProductDescription(testDrinkProduct);
        assertEquals("", err, "Description is invalid.");
    }

    @Test
    public void validateProductName_withNullName_returnsError() {
        testDrinkProduct.setName(null);
        String err = productValidator.validateProductName(testDrinkProduct);
        assertEquals(" Name is null.", err, "Name is not null.");
    }

    @Test
    public void validateProductName_withEmptyName_returnsError() {
        testDrinkProduct.setName("");
        String err = productValidator.validateProductName(testDrinkProduct);
        assertEquals(" Name is empty.", err, "Name is not null.");
    }

    @Test
    public void validateProductName_nameExceedsCharacterLimit_returnsError() {
        testDrinkProduct.setName("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        String err = productValidator.validateProductName(testDrinkProduct);
        assertEquals(" Name must be less than 50 characters.", err, "Name is valid.");
    }

    @Test
    public void validateProductName_withValidName_returnsEmptyString() {
        String err = productValidator.validateProductName(testDrinkProduct);
        assertEquals("", err, "Name is invalid.");
    }

    @Test
    public void validateProductVendorID_withNullClassification_returnsError() {
        testDrinkProduct.setClassification(null);
        String err = productValidator.validateProductVendorID(testDrinkProduct);
        assertEquals(" VendorID could not be validated.", err, "Classification is not null.");
    }

    @Test
    public void validateProductVendorID_withBakedGoodProduct_usingNullVendorID_returnsError() {
        testBakedGoodProduct.setVendorId(null);
        String err = productValidator.validateProductVendorID(testBakedGoodProduct);
        assertEquals(" Vendor ID is null.", err, "Vendor ID is not null.");
    }

    @Test
    public void validateProductVendorID_withBakedGoodProduct_usingEmptyVendorID_returnsError() {
        testBakedGoodProduct.setVendorId("");
        String err = productValidator.validateProductVendorID(testBakedGoodProduct);
        assertEquals(" Vendor ID is empty.", err, "Vendor ID is not empty.");
    }

    @Test
    public void validateProductVendorID_withBakedGoodProduct_usingValidVendorID_returnsEmptyString() {
        testBakedGoodProduct.setVendorId("null");
        String err = productValidator.validateProductVendorID(testBakedGoodProduct);
        assertEquals("", err, "Vendor ID is not null.");
    }

    @Test
    public void validateProductVendorID_withDrinkProduct_usingValidVendorID_returnsEmptyString() {
        testBakedGoodProduct.setVendorId("null");
        String err = productValidator.validateProductVendorID(testBakedGoodProduct);
        assertEquals("", err, "Vendor ID is not null.");
    }

    @Test
    public void validateProductIngredientsList_withNullIngredientsList_returnsError() {
        testDrinkProduct.setIngredientsList(null);
        String err = productValidator.validateProductIngredientsList(testDrinkProduct);
        assertEquals(" IngredientsList is null.", err, "IngredientsList is not null.");
    }

    @Test
    public void validateProductIngredientsList_withEmptyIngredientsList_returnsError() {
        List<String> emptyList = new ArrayList<>();
        testDrinkProduct.setIngredientsList(emptyList);
        String err = productValidator.validateProductIngredientsList(testDrinkProduct);
        assertEquals(" IngredientsList is empty.", err, "IngredientList is not empty.");
    }

    @Test
    public void validateProductIngredientsList_withValidIngredientsList_returnsEmptyString() {
        String err = productValidator.validateProductIngredientsList(testDrinkProduct);
        assertEquals("", err, "IngredientList is invalid.");
    }

    @Test
    public void validateProductClassification_withNullClassification_returnsError() {
        testDrinkProduct.setClassification(null);
        String err = productValidator.validateProductClassification(testDrinkProduct);
        assertEquals(" Classification is null.", err, "Classification is not null.");
    }

    @Test
    public void validateProductClassification_withEmptyClassification_returnsError() {
        testDrinkProduct.setClassification("");
        String err = productValidator.validateProductClassification(testDrinkProduct);
        assertEquals(" Classification is empty.", err, "Classification is not empty.");
    }

    @Test
    public void validateProductClassification_asInvalidClassification_returnsError() {
        testDrinkProduct.setClassification("Invalid Classification");
        String err = productValidator.validateProductClassification(testDrinkProduct);
        assertEquals(" Classification must be Drink or Baked Good.", err, "Classification is valid.");
    }

    @Test
    public void validateProductClassification_asBakedGood_returnsEmptyString () {
        testDrinkProduct.setClassification("Baked Good");
        String err = productValidator.validateProductClassification(testDrinkProduct);
        assertEquals("", err, "Classification does not equal Baked Good.");
    }

    @Test
    public void validateProductClassification_asDrink_returnsEmptyString () {
        String err = productValidator.validateProductClassification(testDrinkProduct);
        assertEquals("", err, "Classification does not equal Baked Good.");
    }

    @Test
    public void validateProductType_withNullType_returnsError() {
        testDrinkProduct.setType(null);
        String err = productValidator.validateProductType(testDrinkProduct);
        assertEquals(" Type is null.", err, "Type is not null.");
    }

    @Test
    public void validateProductType_withEmptyType_returnsError() {
        testDrinkProduct.setType("");
        String err = productValidator.validateProductType(testDrinkProduct);
        assertEquals(" Type is empty.", err, "Type is not empty.");
    }

    @Test
    public void validateProductType_withInvalidType_returnsError() {
        testDrinkProduct.setType("Invalid Type");
        String err = productValidator.validateProductType(testDrinkProduct);
        assertEquals(" Type must be Coffee, Tea, or Soda.", err, "Type is valid.");
    }

    @Test
    public void validateProductType_withValidTypeAsBakedGoodProduct_returnsEmptyString() {
        String err = productValidator.validateProductType(testBakedGoodProduct);
        assertEquals("", err, "Product is not valid.");
    }

    @Test
    public void validateProductCost_withNullCost_returnsError() {
        testDrinkProduct.setCost(null);
        String err = productValidator.validateProductCost(testDrinkProduct);
        assertEquals(" Cost is null.", err, "Cost is not null.");
    }

    @Test
    public void validateProductCost_withEmptyCost_returnsError() {
        testDrinkProduct.setCost("");
        String err = productValidator.validateProductCost(testDrinkProduct);
        assertEquals(" Cost is empty.", err, "Cost is not empty.");
    }

    @Test
    public void validateProductCost_withInvalidCost_returnsError() {
        testDrinkProduct.setCost("10.NotValid");
        String err = productValidator.validateProductCost(testDrinkProduct);
        assertEquals(" Cost must be a number.", err, "Cost is valid.");
    }

    @Test
    public void validateProductCost_withValidCost_returnsEmptyString() {
        String err = productValidator.validateProductCost(testDrinkProduct);
        assertEquals("", err, "Cost is not valid.");
    }

    @Test
    public void validateProductMarkup_withNullMarkup_returnsError() {
        testBakedGoodProduct.setMarkup(null);
        String err = productValidator.validateProductMarkup(testBakedGoodProduct);
        assertEquals(" Markup is null.", err, "Markup is not null.");
    }

    @Test
    public void validateProductMarkup_withEmptyMarkup_returnsError() {
        testBakedGoodProduct.setMarkup("");
        String err = productValidator.validateProductMarkup(testBakedGoodProduct);
        assertEquals(" Markup is empty.", err, "Markup is not empty.");
    }

    @Test
    public void validateProductMarkup_withInvalidMarkup_returnsError() {
        testBakedGoodProduct.setMarkup("10.NotValid");
        String err = productValidator.validateProductMarkup(testBakedGoodProduct);
        assertEquals(" Markup must be a whole number.", err, "Markup is valid.");
    }

    @Test
    public void validateProductMarkup_withValidMarkup_returnsEmptyString() {
        String err = productValidator.validateProductMarkup(testDrinkProduct);
        assertEquals("", err, "Markup is not valid.");
    }

    @Test
    public void validateProductAllergenList_withNullList_returnsError() {
        testDrinkProduct.setAllergenList(null);
        String err = productValidator.validateProductAllergenList(testDrinkProduct);
        assertEquals(" AllergenList is null.", err, "AllergenList is not null.");
    }

    @Test
    public void validateProductAllergenList_withEmptyList_returnsEmptyString() {
        List<String> emptyList = new ArrayList<>();
        testDrinkProduct.setAllergenList(emptyList);
        String err = productValidator.validateProductAllergenList(testDrinkProduct);
        assertEquals("", err, "AllergenList is not empty.");
    }

    @Test
    public void validateProductAllergenList_withInvalidList_returnsError() {
        List<String> invalidList = Arrays.asList(
                "Invalid 1",
                "Invalid 2"
        );
        testDrinkProduct.setAllergenList(invalidList);

        String err = productValidator.validateProductAllergenList(testDrinkProduct);
        assertEquals(" AllergenList must contain: Dairy, Soy, Gluten, or Nuts.", err, "AllergenList is valid.");
    }

    @Test
    public void validateProductAllergenList_withValidList_returnsError() {
        String err = productValidator.validateProductAllergenList(testDrinkProduct);
        assertEquals("", err, "AllergenList is invalid.");
    }

    @Test
    public void calculateSalesPrice_withInvalidProduct_throwsError() {
        testDrinkProduct.setCost("InvalidCost");
        assertThrows(NumberFormatException.class, () -> {
            productValidator.calculateSalesPrice(testDrinkProduct);
        });
    }

    @Test
    public void calculateSalesPrice_withValidProduct_returnsFormattedResult() {
        try {
            String value = productValidator.calculateSalesPrice(testBakedGoodProduct);
            assertEquals("5.25", value, "Product is invalid.");
        } catch (Exception e) {
            fail("Exception was thrown.");
        }
    }

    @Test
    public void formatDollarValues_withInvalidProduct_throwsError() {
        assertThrows(NumberFormatException.class, () -> {
            productValidator.formatDollarValues("InvalidString");
        });
    }

    @Test
    public void formatDollarValues_withValidProduct_returnsFormattedResult() {
        String valueToFormat = "30";

        try {
            String value = productValidator.formatDollarValues(valueToFormat);
            assertEquals("30.00", value, "valueToFormat is invalid.");
        } catch (Exception e) {
            fail("Exception was thrown.");
        }
    }

    @Test
    public void validateProduct_withEmptyProduct_returnsErrors() {
        String allErrors = " Description is null. Name is null. VendorID could not be validated. Classification is null. Type could not be validated. Cost is null. Markup could not be validated. IngredientsList is null. AllergenList is null.";
        String err = productValidator.validateProduct(testInvalidProduct);

        assertEquals(allErrors, err, "Product is valid.");
    }

    @Test
    public void validateProduct_withValidProduct_returnsEmptyString() {
        String err = productValidator.validateProduct(testDrinkProduct);
        assertEquals("", err, "Product is invalid.");
    }

    @Test
    public void formatProduct_withDrinkProduct_returnsFormattedProduct() {
            Product result = productValidator.formatProduct(testDrinkProduct);
            assertEquals("n/a", result.getMarkup(), "Product Markup does not have default value.");
            assertEquals("n/a", result.getVendorId(), "Product has Vendor ID.");

    }

    @Test
    public void formatProduct_WithBakedGoodProduct_returnsFormattedProduct() {
        Product result = productValidator.formatProduct(testBakedGoodProduct);
        assertEquals("n/a", result.getType(), "Product type has value.");
    }

    @Test
    public void isUniqueProduct_withDuplicateProduct_returnsError() {
        List<Product> sampleProductList = Arrays.asList(
                testDrinkProduct,
                testDrinkProduct
        );
        String err = productValidator.isUniqueProduct(testDrinkProduct.getName(), sampleProductList);
        assertEquals("Product with matching name already exists.", err, "Product is unique.");
    }

    @Test
    public void isUniqueProduct_withUniqueProduct_returnsEmptyString() {
        List<Product> sampleProductList = Arrays.asList(
                testDrinkProduct,
                testDrinkProduct
        );
        String err = productValidator.isUniqueProduct(testBakedGoodProduct.getName(), sampleProductList);
        assertEquals("", err, "Product is not unique.");
    }
}
