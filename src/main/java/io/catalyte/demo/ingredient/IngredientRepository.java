package io.catalyte.demo.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing Ingredient entities in the database
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameIgnoreCase(String name);
}
