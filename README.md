# Real Estate API

This application exposes endpoints for basic CRUD operations on a model and manages an in memory list of those objects.

## Startup

Open the RealEstateApplication file and use the green play button in the left gutter to start the application.

The server will start on `localhost:8085`

## JSON Body

Use the following as a JSON body for a POST. We do not supply an id or timestamp on the POST, as that is the application's job to manage.

## CreateVendor sample request body for POST
```
{
  "name": "Sample Vendor",
  "address": {
    "street": "123 Main St",
    "street2": "Apt 101",
    "city": "Springfield",
    "state": "IL",
    "zipCode": "62701"
  },
  "contact": {
    "contactName": "John Doe",
    "email": "john.doe@example.com",
    "titleOrRole": "Sales Manager",
    "phone": "1234567890"
  }
}
```

## CreateProduct sample request body for POST
```
{    
    "active": true,
    "description": "The description of the Product.",
    "name": "Product Name",
    "vendorId": 12345,
    "ingredientsList": [
        "Sample Ingredient 1",
        "Sample Ingredient 2",
        "Sample Ingredient 3",
        "Sample Ingredient 4"
    ],
    "classification": "Product Classification",
    "type": "Product Type",
    "cost": 1.5,
    "markup": 0.05,
    "allergenList": [
        "Allergen 1",
        "Allergen 2"
    ]
}
```

## CreateCustomer sample request body for POST
```
{    
    "active": true,
    "name": "Customer Name",
    "emailAddress": "customer.name@email.com",
    "lifetimeSpent": 5000.0
}
```

## FrontEndConfig

The FrontEndConfig class is a Spring configuration class designed to set up Cross-Origin Resource Sharing (CORS) for 
the backend application. This configuration allows the backend to accept requests from the specified front-end origin, 
enabling smooth communication between the two layers of the application.
