package io.catalyte.demo;

import io.catalyte.demo.ingredient.Ingredient;
import io.catalyte.demo.ingredient.IngredientRepository;
import io.catalyte.demo.ingredient.IngredientService;
import io.catalyte.demo.ingredient.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    Ingredient testIngredient;

    @BeforeEach
    public void testIngredientParameters() {
        List<String> sampleAllergenList = Arrays.asList(
                "Nuts",
                "Gluten"
        );

        ingredientService = new IngredientServiceImpl(ingredientRepository);
        testIngredient = new Ingredient(1,true,"Test Ingredient", BigDecimal.valueOf(15.50),10,"lb", sampleAllergenList);
    }
}
