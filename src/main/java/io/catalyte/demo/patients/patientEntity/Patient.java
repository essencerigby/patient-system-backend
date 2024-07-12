package io.catalyte.demo.patients.patientEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String ssn;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String gender;
    private Short age;
    private Short height;
    private Short weight;
    private String insurance;
    private BigDecimal dailyRentalCost;


    // Constructor - empty params
    public Patient() {}

    // Constructors - default values + params
    public Patient(int id, String firstName, String lastName, String ssn,
                   String email, String street, String city,
                   String state, String zip, String gender,
                   Short age, Short height, Short weight,
                   String insurance, BigDecimal dailyRentalCost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.insurance = insurance;
        this.dailyRentalCost = dailyRentalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(BigDecimal dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }
}
