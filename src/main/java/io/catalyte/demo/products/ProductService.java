package io.catalyte.demo.products;

import java.util.List;

public interface ProductService {

    // CRUD methods:

    List<Product> getProducts();

    Product getProductById(int id);

    Product createProduct(Product productToCreate);

    Product editProduct(Product productToEdit, int id);

    void deleteProductById(int id);
}
