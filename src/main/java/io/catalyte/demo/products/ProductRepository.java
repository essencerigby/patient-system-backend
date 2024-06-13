package io.catalyte.demo.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing Product entities in the database
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameIgnoreCase(String name);
}
