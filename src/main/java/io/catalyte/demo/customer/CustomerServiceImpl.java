package io.catalyte.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service implementation & business logic layer.
 * Provides methods for CRUD operations on Customer objects.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    /**
     * Constructs a new instance of CustomerServiceImpl with the specified CustomerRepository.
     *
     * @param customerRepository The CustomerRepository instance to be used by this service.
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A list of all customers in the system.
     */
    public List<Customer> getCustomers() {
        return null; // PLACEHOLDER
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    public Customer getCustomerById(int id) {
        return null; // PLACEHOLDER
    }

    /**
     * Creates a new customer in the repository
     * @param customerToCreate - Customer Object containing unique identifier, name,
     *                         emailAddress, and lifetimeSpent.
     * @return the created customer
     */
    public Customer createCustomer(Customer customerToCreate) {
        // Sample validation below, replace with Customer Validation (RUNNERS-92)
        if (customerToCreate.getName() == null || customerToCreate.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer");
        }

        customerToCreate.setCustomerSince(getTimestamp());
        customerRepository.save(customerToCreate);
        return customerToCreate;
    }

    /**
     * Updates an existing customer.
     *
     * @param id The ID of the customer to update.
     * @param customerToEdit The updated customer data.
     * @return The updated customer.
     */
    public Customer editCustomer(Customer customerToEdit, int id) {
        return null; // PLACEHOLDER
    }

    /**
     * Deletes a customer from the system.
     *
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomerById(int id) {
        // PLACEHOLDER
    }

    public String getTimestamp () {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy"));
    }
}
