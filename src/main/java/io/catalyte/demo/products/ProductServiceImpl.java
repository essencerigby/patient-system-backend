package io.catalyte.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product productToCreate) {
        productRepository.save(productToCreate);
        return productToCreate;
    }
}
