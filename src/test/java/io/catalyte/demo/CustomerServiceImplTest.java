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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    Customer testCustomer;
    Customer testCustomerToEdit;

    List<Customer> sampleCustomers;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
        testCustomer = new Customer(1, true, "Customer Name",
                "customer.name@email.com", 5000.0);
        testCustomerToEdit = new Customer (1,false, "Customer Name To Edit",
                "customer.name@email.com", 5000.0);
    }

    @Test
    public void getCustomers_getAllCustomers_returnsArrayOfCustomers() {
        when(customerRepository.findAll()).thenReturn(sampleCustomers);
        List<Customer> result = customerService.getCustomers();

        assertEquals(result, sampleCustomers);
    }

    @Test
    public void getCustomerById_withValidId_returnsCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(testCustomer));

        Customer result = customerService.getCustomerById(1);

        assertEquals(testCustomer, result, "Customer was not found.");
    }

    @Test
    public void getCustomerById_withInvalidId_throwsError() {
        when(customerRepository.findById(2)).thenReturn(Optional.empty());
        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerById(2);
        });

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode(), "Expected NOT_FOUND Status");
        assertEquals("Customer not found.", result.getReason(), "Expected error message mismatch");
    }

    @Test
    public void getCustomerByName_withValidName_returnsCustomer() {
        sampleCustomers = Collections.singletonList(testCustomer);
        when(customerRepository.findByNameIgnoreCase("Customer Name")).thenReturn(sampleCustomers);

        List<Customer> result = customerService.getCustomerByName("Customer Name");

        assertEquals(testCustomer.getName(), result.get(0).getName());
    }

    @Test
    public void getCustomerByName_withNonExistentName_throwsError() {
        String nonExistentName = "John Doe";

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerByName(nonExistentName);
        });

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode(), "Expected NOT_FOUND Status");
        assertEquals("A customer with this name wasn't found.", result.getReason());
    }

    @Test
    public void getCustomerByName_withEmptyName_throwsError() {
        testCustomer.setName("");

        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerByName(testCustomer.getName());
        });

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode(), "Expected BAD_REQUEST Status");
        assertEquals("Please ensure a valid name is provided.", result.getReason());
    }

    @Test
    public void getCustomerByName_withNullValue_throwsError() {
        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerByName(null);
        });

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode(), "Expected BAD_REQUEST Status");
        assertEquals("Please ensure a valid name is provided.", result.getReason());
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

    @Test
    public void editCustomer_whenCustomerIdIsValid_shouldReturnObject() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(testCustomerToEdit)).thenReturn(testCustomerToEdit);
        Customer editedCustomer = customerService.editCustomer(testCustomerToEdit, 1);
        assertEquals(testCustomerToEdit.getName(), editedCustomer.getName());
    }

    @Test
    public void editCustomer_whenCustomerIdIsNotValid_shouldReturn404Error() {
        int invalidID = 25; // Assuming this customer ID does not exist
        when(customerRepository.findById(invalidID)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.editCustomer(testCustomerToEdit, invalidID);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"Customer not found.\"", exception.getMessage());
    }
}
