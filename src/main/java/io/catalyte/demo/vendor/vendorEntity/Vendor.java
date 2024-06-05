package io.catalyte.demo.vendor.vendorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vendors")
public class Vendor {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    // Constructor - empty params
    public Vendor() {}

    // Constructors - default values + params
    public Vendor(String name, Address address,
                  Contact contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    private Address address;

    private Contact contact;

    private String createdAt;

    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(
            Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(
            Contact contact) {
        this.contact = contact;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
