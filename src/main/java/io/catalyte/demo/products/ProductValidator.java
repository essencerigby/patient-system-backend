package io.catalyte.demo.products;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class ProductValidator {
    DecimalFormat df = new DecimalFormat("#.00");

    /**
     * Validates that Product Description is not null, empty, and greater than 100 characters
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductDescription(Product productToValidate) {
        if (productToValidate.getDescription() == null) {
            return "-Description is null.";
        } else if (productToValidate.getDescription().isEmpty()) {
            return "-Description is empty.";
        } else if (productToValidate.getDescription().length() > 100) {
            return "-Description must be less than 100 characters.";
        }
        return "";
    }

    /**
     * Validates that Product Name is not null, empty, and less than 50 characters
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductName(Product productToValidate) {
        if (productToValidate.getName() == null) {
            return "-Name is null.";
        } else if (productToValidate.getName().isEmpty()) {
            return "-Name is empty.";
        } else if (productToValidate.getName().length() > 50) {
            return "-Name must be less than 50 characters.";
        }
        return "";
    }

    /**
     * Validates that Product Vendor ID is not null or empty based on Product Classification
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductVendorID(Product productToValidate) {
        if (productToValidate.getClassification() != null) {
            if (productToValidate.getClassification().equals("Baked Good")) {
                if (productToValidate.getVendorId() == null) {
                    return "-Vendor ID is null.";
                }
                else if (productToValidate.getVendorId().isEmpty()) {
                    return "-Vendor ID is empty.";
                }
                else {
                    return "";
                }
            }
            return "";
        }
        return "-VendorID could not be validated.";
    }

    /**
     * Validates that Product Ingredient List is not null or empty
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductIngredientsList(Product productToValidate) {
        if (productToValidate.getIngredientsList() == null) {
            return "-IngredientsList is null.";
        } else if (productToValidate.getIngredientsList().isEmpty()) {
            return "-IngredientsList is empty.";
        }
        return "";
    }

    /**
     * Validates that Product Classification is not null, empty, and equals "Drink" or "Baked Good"
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductClassification(Product productToValidate) {
        if (productToValidate.getClassification() == null) {
            return "-Classification is null.";
        } else if (productToValidate.getClassification().isEmpty()) {
            return "-Classification is empty.";
        } else if (productToValidate.getClassification().equals("Drink") || productToValidate.getClassification().equals("Baked Good")) {
            return "";
        }
        return "-Classification must be Drink or Baked Good.";
    }

    /**
     * Validates that Product Type is not null, empty, and equals Coffee, Tea, or Soda based on Classification
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductType(Product productToValidate) {
        List<String> drinkTypes = Arrays.asList(
                "Coffee",
                "Tea",
                "Soda"
        );

        if (productToValidate.getClassification() != null) {
            if (productToValidate.getClassification().equals("Drink")) {
                if (productToValidate.getType() == null) {
                    return "-Type is null.";
                } else if (productToValidate.getType().isEmpty()) {
                    return "-Type is empty.";
                } else {
                    for (String drink : drinkTypes) {
                        if (productToValidate.getType().equals(drink)) {
                            return "";
                        }
                    }
                }
                return "-Type must be Coffee, Tea, or Soda.";
            }
            return "";
        }
        return "-Type could not be validated.";
    }

    /**
     * Validates that Product Cost is not null, empty, and is a number
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductCost(Product productToValidate) {
        if (productToValidate.getCost() == null) {
            return "-Cost is null.";
        } else if (productToValidate.getCost().isEmpty()) {
            return "-Cost is empty.";
        } else {
            try {
                formatDollarValues(productToValidate.getCost());
            } catch (NumberFormatException e) {
                return "-Cost must be a number.";
            }
            return "";
        }
    }

    /**
     * Validates that Product Markup is not empty, null, and is a number based on Classification
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductMarkup(Product productToValidate) {
        if (productToValidate.getClassification() != null) {
            if (productToValidate.getClassification().equals("Baked Good")) {
                if (productToValidate.getMarkup() == null) {
                    return "-Markup is null.";
                } else if (productToValidate.getMarkup().isEmpty()) {
                    return "-Markup is empty.";
                } else {
                    try {
                        formatDollarValues(productToValidate.getMarkup());
                    } catch (NumberFormatException e) {
                        return "-Markup must be a number.";
                    }
                }
            }
            return "";
        }
        return "-Markup could not be validated.";
    }

    /**
     * Validates that Product Allergen List is not null and contains Dairy, Soy, Gluten, or Nuts
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProductAllergenList(Product productToValidate) {
        List<String> allergens = Arrays.asList(
                "Dairy",
                "Soy",
                "Gluten",
                "Nuts"
        );
        List<String> allergensToValidate = productToValidate.getAllergenList();

        if (productToValidate.getAllergenList() == null) {
            return "-AllergenList is null.";
        } else if (!productToValidate.getAllergenList().isEmpty()) {
            for (String allergen : allergensToValidate) {
                if (!allergens.contains(allergen)) {
                    return "-AllergenList must contain: Diary, Soy, Gluten, or Nuts.";
                }
            }
            return "";
        }
        return "";
    }

    /**
     * Calculates Product Sale Price based on Cost and Markup (If Baked Good Product)
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return a String containing calculated value in x.xx format
     */
    public String calculateSalesPrice(Product productToValidate) {
        if (!productToValidate.getClassification().equals("Baked Good")) {
            return formatDollarValues(String.valueOf(productToValidate.getCost()));
        }

        double markup = Double.parseDouble(productToValidate.getMarkup());
        double cost = Double.parseDouble(productToValidate.getCost());
        double salePrice = (cost * (markup/100)) + cost;

        return formatDollarValues(String.valueOf(salePrice));
    }

    /**
     * Calculates Product Sale Price based on Cost and Markup (If Baked Good Product)
     * @param valueToFormat - Numerical value in type String to be formatted
     * @return a String containing numerical value in x.xx format
     */
    public String formatDollarValues(String valueToFormat) {
        double convertedDoubleFromString = Double.parseDouble(valueToFormat);
        return df.format(convertedDoubleFromString);
    }

    /**
     * Validates all Product Fields
     * @param productToValidate - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return an error String according to supplied Product
     */
    public String validateProduct(Product productToValidate) {
            String error1 = validateProductDescription(productToValidate);
            String error2 = validateProductName(productToValidate);
            String error3 = validateProductVendorID(productToValidate);
            String error4 = validateProductClassification(productToValidate);
            String error5 = validateProductType(productToValidate);
            String error6 = validateProductCost(productToValidate);
            String error7 = validateProductMarkup(productToValidate);
            String error8 = validateProductIngredientsList(productToValidate);
            String error9 = validateProductAllergenList(productToValidate);

            return error1 +
                    error2 +
                    error3 +
                    error4 +
                    error5 +
                    error6 +
                    error7 +
                    error8 +
                    error9;
    }

    /**
     * Formats a Product's values and applies default values based on Classification
     * @param productToFormat - Product Object containing unique identifier, active status, name,
     *                        imageUrl, vendorId, ingredientsList, classification, cost, allergenList,
     *                        and salePrice
     * @return the formatted Product
     */
    public Product formatProduct(Product productToFormat) {
        if (productToFormat.getClassification().equals("Baked Good")) {
            productToFormat.setType("n/a");
        }
        else {
            productToFormat.setMarkup("n/a");
            productToFormat.setVendorId("n/a");
        }

        productToFormat.setCost(formatDollarValues(productToFormat.getCost()));
        productToFormat.setSalePrice(calculateSalesPrice(productToFormat));

        return productToFormat;
    }

    /**
     * Validates that Product with matching name (Case insensitive) does not already exist
     * @param productName - The name of the new Product
     * @param listOfProducts - The list of all persisted Products
     * @return an error String based on result of the search
     */
    public String isUniqueProduct(String productName, List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return "Product with matching name already exists.";
            }
        }
        return "";
    }
}
