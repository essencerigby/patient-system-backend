package io.catalyte.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Customer entities in the database
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
