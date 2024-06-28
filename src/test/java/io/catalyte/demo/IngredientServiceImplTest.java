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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    Ingredient testIngredient;
    Ingredient testIngredient2;

    @BeforeEach
    public void setUp() {
        List<String> sampleAllergenList = Arrays.asList(
                "Nuts",
                "Gluten"
        );

        ingredientService = new IngredientServiceImpl(ingredientRepository);
        testIngredient = new Ingredient(1,true,"Test Ingredient", BigDecimal.valueOf(15.50),BigDecimal.valueOf(10.50),"lb", sampleAllergenList);
        testIngredient2 = new Ingredient(2,true,"Test Ingredient 2", BigDecimal.valueOf(20.99), BigDecimal.valueOf(2),"oz", sampleAllergenList);
    }

    @Test
    public void createIngredient_withNoValidation_PersistIngredient() {
        when(ingredientRepository.save(testIngredient)).thenReturn(testIngredient);

        Ingredient result = ingredientService.createIngredient(testIngredient);

        assertEquals("Test Ingredient", result.getName());
        assertEquals(true, result.getActive());
        assertEquals("lb", result.getUnitOfMeasure());
        assertEquals(Arrays.asList("Nuts", "Gluten"), result.getAllergens());
    }

    @Test
    public void getIngredients_withNoIngredientsPresent_returnsEmptyArray() {
        when(ingredientRepository.findAll()).thenReturn(Arrays.asList());

        List <Ingredient> result = ingredientService.getIngredients();

        assertEquals(0, result.size());
        assertNotNull(result);
    }

    @Test
    public void getIngredients_withIngredientsPresent_returnsAllIngredients() {
        List<Ingredient> expectedIngredients = Arrays.asList(testIngredient, testIngredient2);
        when(ingredientRepository.findAll()).thenReturn(expectedIngredients);

        List <Ingredient> result = ingredientService.getIngredients();

        assertEquals(2, result.size());
        assertEquals(expectedIngredients, result);
    }
}
