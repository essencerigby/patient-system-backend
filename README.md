# Real Estate API

This application exposes endpoints for basic CRUD operations on a model and manages an in memory list of those objects.

## Startup

Open the RealEstateApplication file and use the green play button in the left gutter to start the application.

The server will start on `localhost:8085`

## JSON Body

Use the following as a JSON body for a POST. We do not supply an id or timestamp on the POST, as that is the application's job to manage.
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
