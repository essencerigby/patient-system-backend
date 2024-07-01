package io.catalyte.demo.ingredient;

import io.catalyte.demo.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientController {
    /**
     * A controller class to map CRUD functions from IngredientService to RESTful endpoints
     * Autowired to IngredientServiceImpl (service class)
     * */

    IngredientService ingredientService;

    /**
     * @param ingredientService - the service for performing CRUD methods on Ingredient instances
     * */
    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Retrieves all ingredients
     * @return list of all ingredients
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    /**
     * Retrieves a ingredient by its ID.
     *
     * @param id The ID of the ingredient to retrieve.
     * @return The ingredient with the specified ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ingredient getIngredientById(@PathVariable int id) {
        return ingredientService.getIngredientById(id);
    }

    /**
     * Creates a new ingredient in the repository
     * @param ingredientToCreate - Ingredient Object containing unique identifier, active status, name,
     *                           purchasing cost, amount, unit of measure, and allergens.
     * @return the created ingredient
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestBody Ingredient ingredientToCreate) {
        return ingredientService.createIngredient(ingredientToCreate);
    }

    /**
     * Deletes a ingredient from the system.
     *
     * @param id The ID of the ingredient to delete.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredientById(@PathVariable int id) {
        ingredientService.deleteIngredientById(id);
    }

/**
     * Updates an existing ingredient.
     *
     * @param id               The ID of the ingredient to update.
     * @param ingredientToEdit The updated ingredient data.
     * @return The updated ingredient.
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ingredient editIngredient(@RequestBody Ingredient ingredientToEdit, @PathVariable int id) {
        return ingredientService.editIngredient(ingredientToEdit, id);
    }
}

