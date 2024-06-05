package io.catalyte.demo.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Product entities in the database
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
