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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    Product testProduct1;
    Product testProduct2;
    Product testProduct3;
    Product testProduct4;
    List<Product> testProducts;

    Product testProduct;
    List<String> sampleList;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
        testProduct = new Product(1, true, "",
                "TestName", 5, sampleList,
                "", 5.0, sampleList, 5.0, 5.0);

        testProduct1 = new Product();
        testProduct1.setName("Basketball");
        testProduct2 = new Product();
        testProduct2.setName("Football");
        testProduct3 = new Product();
        testProduct3.setName("Basketball");
        testProduct4 = new Product();
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
    public void getProduct_withValidID_returnsProductWithMatchingID() {
        testProduct.setId(1);

        when(productRepository.findById(testProduct.getId())).thenReturn(Optional.of(testProduct));
        Product result = productService.getProductById(1);
        assertEquals(testProduct, result);
    }

    @Test
    public void getProduct_withInvalidID_returnsErrorWithMessage() {
        testProduct.setId(18);

        when(productRepository.findById(testProduct.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            productService.getProductById(testProduct.getId());
        }, "Product not found.");
    }

    @Test
    public void getProductByName_whenNameExists_shouldReturnProduct() {
        testProducts = Arrays.asList(testProduct2);
        when(productRepository.findByNameIgnoreCase("Football")).thenReturn(testProducts);

        List<Product> result = productService.getProductByName("Football");

        assertEquals(testProduct2.getName(), result.get(0).getName());
    }

    @Test
    public void getProductByName_whenNameDoesntExist_shouldThrow404Exception() {
        testProducts = Arrays.asList();
        when(productRepository.findByNameIgnoreCase("Football")).thenReturn(testProducts);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                productService.getProductByName("Football"));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void getProductByName_whenNameIsNullOrEmpty_shouldThrow400Exception() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                productService.getProductByName(""));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void getProductByName_whenMultipleNamesExist_shouldReturnAllProductsWithThatName() {
        testProducts = Arrays.asList(testProduct1, testProduct3);
        when(productRepository.findByNameIgnoreCase("Basketball")).thenReturn(testProducts);

        List<Product> result = productService.getProductByName("Basketball");

        assertTrue(result.size() > 1);
    }
}
