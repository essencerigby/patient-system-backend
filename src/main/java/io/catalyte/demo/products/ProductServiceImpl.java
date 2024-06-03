package io.catalyte.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Product objects.
 */
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    /**
     * Constructs a new instance of ProductServiceImpl with the specified ProductRepository.
     *
     * @param productRepository The ProductRepository instance to be used by this service.
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a list of all products.
     *
     * @return A list of all products in the system.
     */
    public List<Product> getProducts() {
            return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID.
     */
    public Product getProductById(int id) {
        return null; // Get Product by ID Logic goes here
    }

    /**
     * Retrieves a product by its name.
     * Exact matches only.
     * @param name The name of the product to retrieve.
     * @return The product(s) with the specified name.
     */
    public List<Product> getProductByName(String name) {
        if (name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name value is empty");
        }

        String processedName = preprocessName(name);
        List<Product> tempList = productRepository.findByNameIgnoreCase(processedName);

        if (!tempList.isEmpty()) {
            return productRepository.findByNameIgnoreCase(processedName);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    /**
     * Creates a new product in the repository
     * @param productToCreate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return the created product
     */
    public Product createProduct(Product productToCreate) {
        // Product Validation goes here, Replace Sample Validation Below.
        if (productToCreate.getName() == null || productToCreate.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Product");
        }

        double markup = productToCreate.getMarkup();
        double cost = productToCreate.getCost();
        double salePrice = (cost * markup) + cost;
        productToCreate.setSalePrice(salePrice);

        productRepository.save(productToCreate);
        return productToCreate;
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
     * Deletes a product from the system.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteProductById(int id) {
        // Delete Product by ID Logic goes here
    }

    private String preprocessName(String name) {
        if (name != null) {
            return name.replace(" ", "%20");
        }
        return name;
    }
}
