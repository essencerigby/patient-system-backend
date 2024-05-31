package io.catalyte.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return null; // PLACEHOLDER
    }

    public Customer getCustomerById(int id) {
        return null; // PLACEHOLDER
    }

    public Customer createCustomer(Customer customerToCreate) {
        // Sample validation below, replace with Customer Validation (RUNNERS-92)
        if (customerToCreate.getName() == null || customerToCreate.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer");
        }

        customerRepository.save(customerToCreate);
        return customerToCreate;
    }

    public Customer editCustomer(Customer customerToEdit, int id) {
        return null; // PLACEHOLDER
    }

    public void deleteCustomerById(int id) {
        // PLACEHOLDER
    }
}
