package io.catalyte.demo.ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean active;
    private String name;
    private BigDecimal purchasingCost;
    private int amount;
    private String unitOfMeasure;

    public Ingredient() {}

    public Ingredient(int id, Boolean active, String name, BigDecimal purchasingCost,
                      int amount, String unitOfMeasure) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.purchasingCost = purchasingCost;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
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

    public BigDecimal getPurchasingCost() {
        return purchasingCost;
    }

    public void setPurchasingCost(BigDecimal purchasingCost) {
        this.purchasingCost = purchasingCost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    private List<String> allergens;
}
