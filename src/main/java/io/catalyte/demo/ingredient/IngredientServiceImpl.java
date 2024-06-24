package io.catalyte.demo.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * @param name The name of the ingredient to retrieve.
     * @return The ingredient(s) with the specified name.
     */
    public List<Ingredient> getIngredientByName(String name) {
        return null; // LOGIC HERE
    }

    /**
     * Creates a new ingredient in the repository
     * @param ingredientToCreate - Ingredient Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return the created product
     */
    public Product createProduct(Product productToCreate) {
        String errorMessage = productValidator.validateProduct(productToCreate);
        if (!errorMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        errorMessage = productValidator.isUniqueProduct(productToCreate.getName(), getProducts());
        if (!errorMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, errorMessage);
        }

        Product formattedProduct = productValidator.formatProduct(productToCreate);

        productRepository.save(formattedProduct);
        return formattedProduct;
    }

    /**
     * Updates an existing product.
     *
     * @param id The ID of the product to update.
     * @param productToEdit The updated product data.
     * @return The updated product.
     */
    public Product editProduct(Product productToEdit, int id) {
        return null; // Edit Product Logic goes here
    }

    /**
     * Deletes an ingredient from the system.
     *
     * @param id The ID of the ingredient to delete.
     * @throws ResponseStatusException NOT_FOUND when an invalid ID is provided.
     */
    public void deleteProductById(int id) { // Deletion logic here }
}
