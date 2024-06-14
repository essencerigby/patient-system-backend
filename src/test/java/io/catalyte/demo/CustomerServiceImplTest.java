package io.catalyte.demo;

import io.catalyte.demo.customer.Customer;
import io.catalyte.demo.customer.CustomerRepository;
import io.catalyte.demo.customer.CustomerService;
import io.catalyte.demo.customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    Customer testCustomer;

    List<Customer> sampleCustomers;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
        testCustomer = new Customer(1, true, "Customer Name",
                "customer.name@email.com", 5000.0);
    }

    @Test
    public void getCustomers_getAllCustomers_returnsArrayOfCustomers() {
        when(customerRepository.findAll()).thenReturn(sampleCustomers);
        List<Customer> result = customerService.getCustomers();

        assertEquals(result, sampleCustomers);
    }

    @Test
    public void createCustomer_withValidCustomer_returnsPersistedCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);
        Customer result = customerService.createCustomer(testCustomer);
        assertEquals("Customer Name", result.getName(), "Customer was invalid");
    }

    @Test
    public void createCustomer_withInvalidCustomer_throwsError() {
        testCustomer.setName("");

        assertThrows(ResponseStatusException.class, () -> {
            customerService.createCustomer(testCustomer);
        }, "Product was saved.");
    }

    @Test
    public void createCustomer_withValidCustomer_createsCustomerSinceField() {
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);
        Customer result = customerService.createCustomer(testCustomer);
        assertFalse(result.getCustomerSince().isEmpty() && result.getCustomerSince() == null);
    }
}
