# Movie Rental Inventory

This application exposes endpoints for basic CRUD operations on a model and manages an in memory list of those objects.

## Startup

Open the RealEstateApplication file and use the green play button in the left gutter to start the application.

The server will start on `localhost:8085`

## JSON Body

Use the following as a JSON body for a POST. We do not supply an id on the POST, as that is the application's job to manage.

## CreateMovie sample request body for POST
```
{
"ssn": "Inside Out",
"gender": "Family-Comedy",
"insurance": "Kelsey Mann",
"dailyRentalCost": "5.99"
}
```
## Unit Testing
1. Navigate to src/test/java/io/catalyte/demo/MoviesServiceImplTests.java
2. Right-click on the green play button in the left gutter
3. Select "Run MoviesServiceImplTests with coverage"

## FrontEndConfig

The FrontEndConfig class is a Spring configuration class designed to set up Cross-Origin Resource Sharing (CORS) for 
the backend application. This configuration allows the backend to accept requests from the specified front-end origin, 
enabling smooth communication between the two layers of the application.
