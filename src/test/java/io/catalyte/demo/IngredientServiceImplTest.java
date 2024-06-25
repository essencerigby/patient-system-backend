package io.catalyte.demo;

import io.catalyte.demo.ingredient.Ingredient;
import io.catalyte.demo.ingredient.IngredientRepository;
import io.catalyte.demo.ingredient.IngredientService;
import io.catalyte.demo.ingredient.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    Ingredient testIngredient;

    @BeforeEach
    public void setUp() {
        List<String> sampleAllergenList = Arrays.asList(
                "Nuts",
                "Gluten"
        );

        ingredientService = new IngredientServiceImpl(ingredientRepository);
        testIngredient = new Ingredient(1,true,"Test Ingredient", BigDecimal.valueOf(15.50),10.50,"lb", sampleAllergenList);
    }

    @Test
    public void createIngredient_withAllParametersNoValidation_PersistIngredient() {
        when(ingredientRepository.save(testIngredient)).thenReturn(testIngredient);

        Ingredient result = ingredientService.createIngredient(testIngredient);

        assertEquals("Test Ingredient", result.getName());
        assertEquals(BigDecimal.valueOf(15.50), result.getPurchasingCost());
        assertEquals(true, result.getActive());
        assertEquals(10.50, result.getAmount());
        assertEquals("lb", result.getUnitOfMeasure());
        assertEquals(Arrays.asList("Nuts", "Gluten"), result.getAllergens());
    }
}
