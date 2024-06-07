package io.catalyte.demo.products;

import java.util.Arrays;
import java.util.List;

public class ProductValidator {
    public String validateProductActive(Product productToValidate) {
        return null;
    }

    public String validateProductDescription(Product productToValidate) {
        if (productToValidate.getDescription() == null) {
            return "Description is null.";
        } else if (productToValidate.getDescription().isEmpty()) {
            return "Description is empty.";
        } else if (productToValidate.getDescription().length() > 100) {
            return "Description must be less than 100 characters.";
        }
        return "";
    }

    public String validateProductName(Product productToValidate) {
        if (productToValidate.getName() == null) {
            return "Name is null.";
        } else if (productToValidate.getName().isEmpty()) {
            return "Name is empty.";
        } else if (productToValidate.getName().length() > 50) {
            return "Name must be less than 50 characters.";
        }
        return "";
    }

    public String validateProductVendorID(Product productToValidate) {
        return null;
    }

    public String validateProductIngredientList(Product productToValidate) {
        return null;
    }

    public String validateProductClassification(Product productToValidate) {
        if (productToValidate.getClassification() == null) {
            return "Classification is null.";
        } else if (productToValidate.getClassification().isEmpty()) {
            return "Classification is empty.";
        } else if (productToValidate.getClassification().equals("Drink") || productToValidate.getClassification().equals("Baked Good")) {
            return "";
        }
        return "Classification must be Drink or Baked Good.";
    }

    public String validateProductType(Product productToValidate) {
        List<String> drinkTypes = Arrays.asList(
                "Coffee",
                "Tea",
                "Soda"
        );

        if (productToValidate.getType() == null) {
            return "Type is null.";
        } else if (productToValidate.getType().isEmpty()) {
            return "Type is empty.";
        } else {
            for (String drink : drinkTypes) {
                if (productToValidate.getType().equals(drink)) {
                    return "";
                }
            }
        }
        return "Type must be Coffee, Tea, or Soda.";
    }

    public String validateProductCost(Product productToValidate) {
        return null;
    }

    public String validateProductMarkup(Product productToValidate) {
        return null;
    }

    public String validateProductAllergenList(Product productToValidate) {
        return null;
    }
}
