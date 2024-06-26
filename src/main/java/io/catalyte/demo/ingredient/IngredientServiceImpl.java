package io.catalyte.demo.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Ingredient objects.
 */
@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;
    IngredientValidator ingredientValidator = new IngredientValidator();

    /**
     * Constructs a new instance of IngredientServiceImpl with the specified IngredientRepository.
     *
     * @param ingredientRepository The IngredientRepository instance to be used by this service.
     */
    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Retrieves a list of all ingredients.
     *
     * @return A list of all ingredients in the system.
     */
    public List<Ingredient> getIngredients() {
        return null; // LOGIC HERE
    }

    /**
     * Retrieves an ingredient by its ID.
     *
     * @param id The ID of the ingredient to retrieve.
     * @return The ingredient with the specified ID.
     */
    public Ingredient getIngredientById(int id) {
        return null; // LOGIC HERE
    }

    /**
     * Retrieves an ingredient by its name.
     * Exact matches only.
     *
     * @param name The name of the ingredient to retrieve.
     * @return The ingredient(s) with the specified name.
     */
    public List<Ingredient> getIngredientByName(String name) {
        return null; // LOGIC HERE
    }

    /**
     * Creates a new ingredient in the repository
     *
     * @param ingredientToCreate - Ingredient Object containing a unique identifier, active status, name,
     *                           purchasing cost, amount, and unit of measurement.
     * @return the created ingredient
     */
    public Ingredient createIngredient(Ingredient ingredientToCreate) {
        String formattedAmount = ingredientValidator.formatAmount(ingredientToCreate.getAmount());
        ingredientToCreate.setAmount(formattedAmount);
        return ingredientRepository.save(ingredientToCreate);
    }

    /**
     * Updates an existing ingredient.
     *
     * @param id               The ID of the ingredient to update.
     * @param ingredientToEdit The updated ingredient data.
     * @return The updated ingredient.
     */
    public Ingredient editIngredient(Ingredient ingredientToEdit, int id) {
        return null; // LOGIC HERE
    }

    /**
     * Deletes an ingredient from the system.
     *
     * @param id The ID of the ingredient to delete.
     * @throws ResponseStatusException NOT_FOUND when an invalid ID is provided.
     */
    public void deleteIngredientById(int id) { // LOGIC HERE }
    }
}
