# Car Dealership Inventory System

A full-stack Car Dealership Inventory System built as part of the Incubyte assessment.

The application enables administrators to manage dealership inventory while allowing authenticated users to browse, search, and purchase vehicles.

The backend has been developed using Spring Boot following Test-Driven Development (TDD), SOLID principles, and Clean Code practices.


## Features

### Authentication

- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization (ADMIN / USER)

### Vehicle Management

- Add Vehicle
- Update Vehicle
- Delete Vehicle
- Purchase Vehicle
- Restock Vehicle
- Search Vehicles
- View All Vehicles

### Testing

- Unit Tests
- Repository Tests
- Integration Tests
- Security Tests


## Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- Maven

### Testing

- JUnit 5
- Mockito
- MockMvc
- H2 Database
- JaCoCo

### Frontend

- React
- Tailwind CSS


## Project Structure

backend
├── auth
├── config
├── exception
├── security
├── vehicle
│ ├── controller
│ ├── dto
│ ├── entity
│ ├── mapper
│ ├── repository
│ ├── service
│ └── validator
└── util



## Architecture

The backend follows a layered architecture.

Controller

↓

Service

↓

Repository

↓

Database

DTOs are used for communication between clients and controllers.

Mappers convert DTOs into entities and entities into DTOs.

Business rules are implemented inside the service layer.

Repositories are responsible only for persistence.


## Authentication

Authentication uses JSON Web Tokens (JWT).

Protected APIs require a valid Bearer Token.

Role-based authorization is enforced using Spring Security.

ADMIN

- Create Vehicle
- Update Vehicle
- Delete Vehicle
- Restock Vehicle

USER

- View Vehicles
- Search Vehicles
- Purchase Vehicle


## Vehicle APIs

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /api/auth/register | Public |
| POST | /api/auth/login | Public |
| POST | /api/vehicles | ADMIN |
| GET | /api/vehicles | USER / ADMIN |
| GET | /api/vehicles/search | USER / ADMIN |
| PUT | /api/vehicles/{id} | ADMIN |
| DELETE | /api/vehicles/{id} | ADMIN |
| POST | /api/vehicles/{id}/purchase | USER / ADMIN |
| POST | /api/vehicles/{id}/restock | ADMIN |


## Running Locally

1. Clone the repository.

2. Configure PostgreSQL.

3. Update application.properties.

4. Run

mvn spring-boot:run

5. Access APIs using Postman.


## Testing

Run all tests

mvn test

Generate code coverage

mvn verify

Testing includes

- Unit Tests
- Repository Tests
- Integration Tests
- Security Tests


## TDD Workflow

Each feature was developed using the Red → Green → Refactor cycle.

1. RED
   Write a failing test.

2. GREEN
   Write the minimum implementation required to pass the test.

3. REFACTOR
   Improve the design while keeping all tests green.


   ## AI Usage

AI assistance was used to

- Discuss architecture
- Review design decisions
- Explain Spring Security concepts
- Suggest TDD workflow
- Improve documentation
- Review code quality

All production code was reviewed, tested, and validated before being committed.

## Future Improvements

- React Frontend
- Pagination
- Sorting
- Image Upload
- Docker Support
- CI/CD Pipeline
- Swagger Documentation