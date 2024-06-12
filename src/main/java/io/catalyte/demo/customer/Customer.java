package io.catalyte.demo.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean active;
    private String name;
    private String emailAddress;
    private Double lifetimeSpent;
    private String customerSince;

    public Customer() {
    }

    public Customer(int id, Boolean active, String name, String emailAddress, Double lifetimeSpent) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.emailAddress = emailAddress;
        this.lifetimeSpent = lifetimeSpent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Double getLifetimeSpent() {
        return lifetimeSpent;
    }

    public void setLifetimeSpent(Double lifetimeSpent) {
        this.lifetimeSpent = lifetimeSpent;
    }

    public String getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(String customerSince) {
        this.customerSince = customerSince;
    }
}
