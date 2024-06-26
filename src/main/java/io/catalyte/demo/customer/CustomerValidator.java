package io.catalyte.demo.customer;

import java.text.DecimalFormat;
import java.util.List;

/**
 * This class provides validation methods for a Customer object.
 */
public class CustomerValidator {
    DecimalFormat df = new DecimalFormat("#.00");
    private Customer customerToValidate;

    public CustomerValidator(Customer customerToValidate) {
        this.customerToValidate = customerToValidate;
    }

    /**
     * Validates the active status of the customer.
     *
     * @return an error message if the active status is null, otherwise an empty string
     */
    public String validateCustomerActiveStatus() {
        if (customerToValidate.getActive() == null) {
            return " Customer Active Status is null.";
        }
        return "";
    }

    /**
     * Validates the name of the customer.
     *
     * @return an error message if the name is null, empty, or exceeds 50 characters, otherwise an empty string
     */
    public String validateCustomerName() {
        String name = customerToValidate.getName();
        if (name == null) {
            return " Customer Name is null.";
        } else if (name.isEmpty()) {
            return " Customer Name is blank.";
        } else if (name.length() > 50) {
            return " Customer Name must be less than 50 characters.";
        }
        return "";
    }

    /**
     * Validates the email address of the customer.
     *
     * @return an error message if the email address is null or invalid, otherwise an empty string
     */
    public String validateCustomerEmailAddress() {
        String email = customerToValidate.getEmailAddress();
        if (email == null) {
            return " Email Address is null.";
        } else if (!isValidEmail(email)) {
            return " Email Address must be in the following format: x@x.x";
        }
        return "";
    }

    /**
     * Checks if the provided email address is in a valid format.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, otherwise false
     */
    private Boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Validates the lifetime spent by the customer.
     *
     * @return an error message if the lifetime spent is negative, otherwise an empty string
     */
    public String validateCustomerLifeTimeSpent() {
        Double lifetimeSpent = customerToValidate.getLifetimeSpent();
        if (lifetimeSpent < 0) {
            return " Lifetime Spent must be a non-negative value.";
        }
        return "";
    }


    /**
     * Validates all fields of the customer.
     *
     * @param customerToValidate the Customer object to validate
     * @return concatenated error messages if any field is invalid, otherwise an empty string
     */
    public String validateCustomer(Customer customerToValidate) {
        String error1 = validateCustomerActiveStatus();
        String error2 = validateCustomerName();
        String error3 = validateCustomerEmailAddress();
        String error4 = validateCustomerLifeTimeSpent();

        return error1 + error2 + error3 + error4;
    }

    /**
     * Formats the lifetime spent by the customer.
     *
     * @param customerToFormat the Customer object to format
     * @return the formatted Customer object
     */
    public Customer formatCustomer(Customer customerToFormat) {
        customerToFormat.setLifetimeSpent(
                Double.valueOf(
                        df.format(customerToFormat.getLifetimeSpent())));
        return customerToFormat;
    }

    /**
     * Checks if a customer name is unique in the list of customers.
     *
     * @param customerName the customer name to check
     * @param listOfCustomers the list of customers to check against
     *                        optional @param customerId utilized in editCustomer
     * @return an error message if a matching name already exists, otherwise an empty string
     */
    public String isUniqueName(String customerName, List<Customer> listOfCustomers) {
        return isUniqueName(customerName, listOfCustomers, -1);
    }
    public String isUniqueName(String customerName, List<Customer> listOfCustomers, Integer customerId) {
        for (Customer customer : listOfCustomers) {
            if (customer.getId() != (customerId) && customer.getName().equalsIgnoreCase(customerName)) {
                return " Customer with matching name already exists.";
            }
        }
        return "";
    }
}
