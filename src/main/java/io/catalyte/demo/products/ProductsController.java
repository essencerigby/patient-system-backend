package io.catalyte.demo.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

  ProductService productService;

  @Autowired
  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  private static int idCounter = 1;
  private static List<Product> products = new ArrayList<>();

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProducts() {
    return products;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductById(@PathVariable int id) {
    return products.get(id-1);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product productToCreate) {
    return productService.createProduct(productToCreate);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product editProduct(@RequestBody Product productToEdit, @PathVariable int id) {
    if (products.size() >= id - 1 && productToEdit.getId() == id) {
      products.set(id - 1, productToEdit);
    }

    return productToEdit;
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable int id) {
    products.remove(id - 1);
  }

}
