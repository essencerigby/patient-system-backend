package io.catalyte.demo.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
  /**
   * A controller class to map CRUD functions from ProductService to RESTful endpoints
   * Autowired to ProductServiceImpl (service class)
   * */

  ProductService productService;

  /**
   * @param productService - the service for performing CRUD methods on Customer instances
   * */
  @Autowired
  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Retrieves a list of all products.
   *
   * @return A list of all products in the system.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProducts() {
    return productService.getProducts();
  }

  /**
   * Retrieves a product by its name.
   *
   * @param name The name of the product to retrieve.
   * @return The product(s) with the specified name.
   */
  @GetMapping(params = "name")
  public ResponseEntity<?> getProductByName(@RequestParam(name = "name", required = false) String name) {
    List<Product> products = productService.getProductByName(name);
    return ResponseEntity.ok(products);
  }

  /**
   * Retrieves a product by its ID.
   *
   * @param id The ID of the product to retrieve.
   * @return The product with the specified ID.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductById(@PathVariable int id) {
    return productService.getProductById(id);
  }

  /**
   * Creates a new product in the repository
   * @param productToCreate - Product Object containing unique identifier, active status, name,
   *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
   *                        and salePrice
   * @return the created product
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product productToCreate) {
    return productService.createProduct(productToCreate);
  }

  /**
   * Updates an existing product.
   *
   * @param id The ID of the product to update.
   * @param productToEdit The updated product data.
   * @return The updated product.
   */
  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product editProduct(@RequestBody Product productToEdit, @PathVariable int id) {
    return productService.editProduct(productToEdit, id);
  }

  /**
   * Deletes a product from the system.
   *
   * @param id The ID of the product to delete.
   */
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable int id) {
    productService.deleteProductById(id);
  }
}