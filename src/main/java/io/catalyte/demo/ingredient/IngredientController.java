package io.catalyte.demo.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}