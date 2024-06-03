package io.catalyte.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    /**
     * A controller class to map CRUD functions from CustomerService to RESTful endpoints
     * Autowired to CustomerServiceImpl (service class)
     * */

    CustomerService customerService;

    /**
     * @param customerService - the service for performing CRUD methods on Customer instances
     * */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A list of all customers in the system.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Creates a new customer in the repository
     * @param customerToCreate - Customer Object containing unique identifier, name,
     *                         emailAddress, and lifetimeSpent.
     * @return the created customer
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customerToCreate) {
        return customerService.createCustomer(customerToCreate);
    }

    /**
     * Updates an existing customer.
     *
     * @param id The ID of the customer to update.
     * @param customerToEdit The updated customer data.
     * @return The updated customer.
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer editCustomer(@RequestBody Customer customerToEdit, @PathVariable int id) {
        return customerService.editCustomer(customerToEdit, id);
    }

    /**
     * Deletes a customer from the system.
     *
     * @param id The ID of the customer to delete.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomerById(id);
    }
}
