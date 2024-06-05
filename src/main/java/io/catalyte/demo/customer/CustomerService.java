package io.catalyte.demo.customer;

import java.util.List;

public interface CustomerService {

    // CRUD methods:

    List<Customer> getCustomers();

    Customer getCustomerById(int id);

    Customer createCustomer(Customer customerToCreate);

    Customer editCustomer(Customer customerToEdit, int id);

    void deleteCustomerById(int id);
}