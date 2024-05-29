package io.catalyte.demo.vendor.vendorEntity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {

    private String contactName;
    private String titleOrRole;
    private String phone;
    private String email;

    public Contact() {}

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitleOrRole() {
        return titleOrRole;
    }

    public void setTitleOrRole(String titleOrRole) {
        this.titleOrRole = titleOrRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

}
