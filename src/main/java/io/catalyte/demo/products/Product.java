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
  private String imageUrl;
  private int vendorId;

  @ElementCollection
  private List<String> ingredientsList;
  private String classification;
  private String type;
  private double cost;
  private double markUp;

 @ElementCollection
  private List<String> allergenList;
  private double salePrice;

  public Product() {
  }

  public Product(int id, boolean active, String description, String name,
                 String imageUrl, int vendorId, List<String> ingredientsList,
                 String classification, double cost,
                 List<String> allergenList, double markUp, double salePrice) {
    this.id = id;
    this.active = active;
    this.description = description;
    this.name = name;
    this.imageUrl = imageUrl;
    this.vendorId = vendorId;
    this.ingredientsList = ingredientsList;
    this.classification = classification;
    this.cost = cost;
    this.allergenList = allergenList;
    this.markUp = markUp;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public int getVendorId() {
    return vendorId;
  }

  public void setVendorId(int vendorId) {
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

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public double getMarkUp() {
      return this.markUp;
  }

  public void setMarkUp(double markUp) {
      this.markUp = markUp;
  }

  public List<String> getAllergenList() {
    return allergenList;
  }

  public void setAllergenList(List<String> allergenList) {
    this.allergenList = allergenList;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }
}
