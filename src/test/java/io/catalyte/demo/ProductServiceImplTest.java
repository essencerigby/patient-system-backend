package io.catalyte.demo;

import io.catalyte.demo.products.Product;
import io.catalyte.demo.products.ProductRepository;
import io.catalyte.demo.products.ProductService;
import io.catalyte.demo.products.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    Product testProduct;

    @BeforeEach
    public void setUp() {
        List<String> sampleIngredientList = Arrays.asList(
                "Ingredient 1",
                "Ingredient 2"
        );

        List<String> sampleAllergenList = Arrays.asList(
                "Dairy",
                "Soy"
        );

        productService = new ProductServiceImpl(productRepository);
        testProduct = new Product(1, true, "SampleDescription",
                "TestName", "5", sampleIngredientList,
                "Drink", "Coffee", "5.0", sampleAllergenList, "5.0", "5.0");
    }

    @Test
    public void createProduct_withValidProduct_returnsPersistedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);
        Product result = productService.createProduct(testProduct);
        assertEquals("TestName", result.getName(), "Product was Invalid");
    }

    @Test
    public void createProduct_withInvalidProduct_throwsError() {
        testProduct.setName("");

        assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(testProduct);
        }, "Product was saved.");
    }

    @Test
    public void createProduct_withDuplicateProduct_throwsError() {
        List<Product> sampleProductList = Arrays.asList(
                testProduct,
                testProduct
        );
        when(productRepository.findAll()).thenReturn(sampleProductList);
        assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(testProduct);
        });
    }
}
