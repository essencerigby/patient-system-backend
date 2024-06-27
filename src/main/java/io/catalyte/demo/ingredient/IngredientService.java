package io.catalyte.demo.ingredient;

import java.util.List;

public interface IngredientService {

    // CRUD methods:
    // Commented out code = placeholder for future CRUD methods

    List<Ingredient> getIngredients();

//    Ingredient getIngredientById(int id);

//    List<Ingredient> getIngredientByName(String name);

    Ingredient createIngredient(Ingredient ingredientToCreate);

    Ingredient editIngredient(Ingredient ingredientToEdit, int id);

//    void deleteIngredientById(int id);
}
