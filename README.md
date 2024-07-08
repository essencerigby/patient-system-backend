# Expresso-O Coffee Shop

This application exposes endpoints for basic CRUD operations on a model and manages an in memory list of those objects.

## Startup

Open the RealEstateApplication file and use the green play button in the left gutter to start the application.

The server will start on `localhost:8085`

## JSON Body

Use the following as a JSON body for a POST. We do not supply an id or timestamp on the POST, as that is the application's job to manage.

## CreateMovie sample request body for POST
```
{
"title": text,
"genre": text,
"director": text,
"dailyRentalCost": decimal
}
```


## FrontEndConfig

The FrontEndConfig class is a Spring configuration class designed to set up Cross-Origin Resource Sharing (CORS) for 
the backend application. This configuration allows the backend to accept requests from the specified front-end origin, 
enabling smooth communication between the two layers of the application.
