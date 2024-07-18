# Super Health Patient Data - API

Super Health Inc. is a small regional healthcare company that operates a series of clinics. The company has an existing application for tracking patient data. This application has been in service for a number of years and is in need of a rewrite. Super Health has asked me to rewrite the
application in a modern way. At this point, Super Health Inc. is looking for a proof of concept, and does not require any authentication or authorization.

The intent of this application is for a user in a medical office to create, review, update and delete patient information in the system.


## Pre-requisites

- IntelliJ IDEA (v17.0.10)
  - Postgres (v8.2)
      ```
        server:
            port: 8085
        datasource:
            url: jdbc:postgresql://localhost:5432/patients
            username: postgres
            password: root
            driverClassName: org.postgresql.Driver
      ```
- Node.js and npm installed
- React application setup
- Postman

## Startup

Open the SuperHealthPatientsApplication file and use the green play button in the left gutter to start the application.

The server will start on `localhost:8085`

## Testing the API with Postman
1. Join the postman team here: [https://patients-team.postman.co/workspace/Team-Workspace~053a3390-69f3-434c-8c9c-cdebc1a29a63/collection/34673203-c1018f56-34ab-4dd2-8626-83d16330c095?action=share&creator=34673203](https://patients-team.postman.co/workspace/Team-Workspace~053a3390-69f3-434c-8c9c-cdebc1a29a63/collection/34673203-c1018f56-34ab-4dd2-8626-83d16330c095?action=share&creator=34673203)
2. Ensure SuperHealthPatientsApplication is running
3. Navigate through the CRUD requests

## Unit Testing
1. Navigate to src/test/java/io/catalyte/demo/PatientServiceImplTests.java
2. Right-click on the green play button in the left gutter
3. Select "Run PatientServiceImplTests with coverage"

## FrontEndConfig

The FrontEndConfig class is a Spring configuration class designed to set up Cross-Origin Resource Sharing (CORS) for 
the backend application. This configuration allows the backend to accept requests from the specified front-end origin, 
enabling smooth communication between the two layers of the application.
