package io.catalyte.demo.products;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private boolean active;
  private String description;
  private String name;
  private String vendorId;

  @ElementCollection
  private List<String> ingredientsList;
  private String classification;
  private String type;
  private String cost;
  private String markup;

 @ElementCollection
  private List<String> allergenList;
  private String salePrice;

  public Product() {
  }

  public Product(int id, boolean active, String description, String name, String vendorId,
                 List<String> ingredientsList, String classification, String type, String cost,
                 List<String> allergenList, String markup, String salePrice) {
    this.id = id;
    this.active = active;
    this.description = description;
    this.name = name;
    this.vendorId = vendorId;
    this.ingredientsList = ingredientsList;
    this.classification = classification;
    this.type = type;
    this.cost = cost;
    this.allergenList = allergenList;
    this.markup = markup;
    this.salePrice = salePrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public List<String> getIngredientsList() {
    return ingredientsList;
  }

  public void setIngredientsList(List<String> ingredientsList) {
    this.ingredientsList = ingredientsList;
  }

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public String getType() {
      return this.type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public String getMarkup() {
      return this.markup;
  }

  public void setMarkup(String markup) {
      this.markup = markup;
  }

  public List<String> getAllergenList() {
    return allergenList;
  }

  public void setAllergenList(List<String> allergenList) {
    this.allergenList = allergenList;
  }

  public String getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(String salePrice) {
    this.salePrice = salePrice;
  }
}
