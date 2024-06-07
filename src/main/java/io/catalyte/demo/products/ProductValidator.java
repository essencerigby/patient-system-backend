package io.catalyte.demo.products;

public class ProductValidator {
    public String validateProductActive(Product productToValidate) {
        return null;
    }

    public String validateProductDescription(Product productToValidate) {
        if (productToValidate.getDescription() == null) {
            return "Description is null.";
        } else if (productToValidate.getDescription().length() > 100) {
            return "Description must be less than 100 characters.";
        } else if (productToValidate.getDescription().isEmpty()) {
            return "Description is empty.";
        }
        return "";
    }

    public String validateProductName(Product productToValidate) {
        if (productToValidate.getName() == null) {
            return "Name is null.";
        } else if (productToValidate.getName().length() > 50) {
            return "Name must be less than 50 characters.";
        } else if (productToValidate.getName().isEmpty()) {
            return "Name is empty.";
        }
        return "";
    }

    public String validateProductVendorID(Product productToValidate) {
        return null;
    }

    public String validateProductIngredientList(Product productToValidate) {
        return null;
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
