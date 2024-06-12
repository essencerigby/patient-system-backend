package io.catalyte.demo.products;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class ProductValidator {
    DecimalFormat df = new DecimalFormat("#.00");

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

    public String validateProductIngredientsList(Product productToValidate) {
        if (productToValidate.getIngredientsList() == null) {
            return "-IngredientsList is null.";
        } else if (productToValidate.getIngredientsList().isEmpty()) {
            return "-IngredientsList is empty.";
        }
        return "";
    }

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

    public String calculateSalesPrice(Product productToValidate) {
        double markup = Double.parseDouble(productToValidate.getMarkup());
        double cost = Double.parseDouble(productToValidate.getCost());
        double salePrice = (cost * (markup/100)) + cost;

        return formatDollarValues(String.valueOf(salePrice));
    }

    public String formatDollarValues(String valueToFormat) {
        double convertedDoubleFromString = Double.parseDouble(valueToFormat);
        return df.format(convertedDoubleFromString);
    }

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

    public Product formatProduct(Product productToFormat) {
            if (productToFormat.getClassification().equals("Baked Good")) {
                productToFormat.setType("-");
                productToFormat.setSalePrice(calculateSalesPrice(productToFormat));
            }
            else {
                productToFormat.setMarkup("0");
                productToFormat.setVendorId("-");
                productToFormat.setSalePrice(calculateSalesPrice(productToFormat));
            }

            return productToFormat;
    }

    public String isUniqueProduct(String productName, List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return "Product with matching name already exists.";
            }
        }
        return "";
    }
}
