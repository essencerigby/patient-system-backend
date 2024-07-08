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
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    public Customer getCustomerById(int id) {
        try {
            return customerRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
    }

    /**
     * Retrieves a customer by its name.
     *
     * @param name The name of the customer to retrieve; must be exact (i.e. First Last),
     *             not case-sensitive.
     * @return All customers with the specified name.
     * @throws ResponseStatusException if name is empty or null.
     * @throws ResponseStatusException if a customer with the name wasn't found.
     */
    public List<Customer> getCustomerByName(String name) {
        if(name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please ensure a valid name is provided.");
        }

        List<Customer> tempList = customerRepository.findByNameIgnoreCase(name);

        if(!tempList.isEmpty()) {
            return customerRepository.findByNameIgnoreCase(name);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A customer with this name wasn't found.");
    }

    /**
     * Creates a new customer in the repository
     * @param customerToCreate - Customer Object containing unique identifier, name,
     *                         emailAddress, and lifetimeSpent.
     * @return the created customer
     */
    public Customer createCustomer(Customer customerToCreate) {
        CustomerValidator customerValidator = new CustomerValidator(customerToCreate);
        customerToCreate.setCustomerSince(getTimestamp());
        if (customerToCreate.getLifetimeSpent() == null) {
            customerToCreate.setLifetimeSpent(0.0);
        } else {
            customerToCreate.setLifetimeSpent(customerToCreate.getLifetimeSpent());
        }

        // Collecting error messages
        String errorMessage = customerValidator.validateCustomer(customerToCreate);
        errorMessage += customerValidator.isUniqueName(customerToCreate.getName(), getCustomers());

        if (!errorMessage.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage);
        }

        Customer formattedCustomer = customerValidator.formatCustomer(customerToCreate);
        customerRepository.save(formattedCustomer);
        return formattedCustomer;
    }

    /**
     * Updates an existing customer.
     *
     * @param id The ID of the customer to update.
     * @param customerToEdit The updated customer data.
     * @return The updated customer.
     */
    public Customer editCustomer(Customer customerToEdit, int id) {

        try {
            getCustomerById(
                    id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }

        Customer existingCustomer = getCustomerById(
                id);
        customerToEdit.setId(id);
        customerToEdit.setCustomerSince(existingCustomer.getCustomerSince());

        if (customerToEdit.getLifetimeSpent() == null) {
            customerToEdit.setLifetimeSpent(0.0);
        } else {
            customerToEdit.setLifetimeSpent(customerToEdit.getLifetimeSpent());
        }

        CustomerValidator customerValidator = new CustomerValidator(customerToEdit);

        // Collecting error messages
        String errorMessage = customerValidator.validateCustomer(customerToEdit);
        errorMessage += customerValidator.isUniqueName(customerToEdit.getName(), getCustomers(), id);

        if (!errorMessage.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage);
        }

        Customer formattedCustomer = customerValidator.formatCustomer(customerToEdit);
        customerRepository.save(formattedCustomer);
        return formattedCustomer;
    }

    /**
     * Deletes a customer from the system.
     *
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomerById(int id) {
        if (getCustomerById(id) != null) {
            customerRepository.deleteById(id);
        }
    }

    /**
     * Returns the current date formatted as a string.
     * The format used is "MM-yyyy", representing the month and year.
     *
     * @return A string representing the current date in "MM-yyyy" format.
     */
    public String getTimestamp () {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy"));
    }
}
