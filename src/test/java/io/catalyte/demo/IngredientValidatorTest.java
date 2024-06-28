package io.catalyte.demo;

import io.catalyte.demo.ingredient.Ingredient;
import io.catalyte.demo.ingredient.IngredientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class IngredientValidatorTest {
    @Mock
    private IngredientValidator ingredientValidator = new IngredientValidator();
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
}
