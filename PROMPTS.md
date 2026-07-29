# PROMPTS.md

## Day 1

### Prompt

How should I structure a production-ready Spring Boot project for a Car Dealership Inventory System using TDD, SOLID principles, and Clean Architecture?

### AI Guidance Summary

- Recommended layered architecture.
- Suggested backend-first development.
- Planned TDD workflow (Red-Green-Refactor).
- Planned Git commit strategy.


## Prompt

How should Phase 1 of the backend be structured for a Spring Boot TDD project?

## Summary

- Planned infrastructure setup before feature development.
- Decided to configure PostgreSQL, JUnit, Mockito, and JaCoCo first.
- Established small, meaningful Git commits following TDD.

## Prompt

How should PostgreSQL be configured for a Spring Boot project that follows TDD and clean architecture?

## Summary

- Created the project database before writing any entities.
- Configured datasource properties.
- Added Hibernate development settings.
- Verified successful application startup.

## Prompt

How should the backend package structure be organized before implementing any business features in a Spring Boot project following TDD and SOLID principles?

## Summary

- Adopted a package-by-feature architecture instead of package-by-layer.
- Created top-level modules for authentication, vehicles, inventory, configuration, security, shared utilities, and exception handling.
- Deferred controllers, services, and repositories until the corresponding feature is developed through TDD.

## Prompt

How should JUnit 5, Mockito, and JaCoCo be prepared before implementing backend features in a Spring Boot project following Test Driven Development?

## Summary

- Added explicit Mockito support for unit testing.
- Verified the JUnit 5 environment with a simple Arrange–Act–Assert test.
- Confirmed JaCoCo generates code coverage reports.
- Established the testing infrastructure before writing business logic.

## Prompt

How should I start implementing user registration using strict Test Driven Development in a Spring Boot application?

## Summary

- Identified the first business requirement for user registration.
- Created the first failing service-level test before writing production code.
- Chose to begin testing at the service layer because it contains the business logic.
- Established the RED phase of the TDD cycle.

## Prompt

How should I begin implementing user registration using strict Test Driven Development in Spring Boot?

## Summary

- Replaced a placeholder failing test with a real business-oriented unit test.
- Introduced request and response DTOs to keep the API independent from persistence.
- Defined the first business requirement: successful user registration.
- Began the RED phase by writing a test before creating the service implementation.

## Prompt

How should I implement the first GREEN step of user registration while following strict TDD?

## Summary

- Implemented only the minimum AuthService required to satisfy the initial registration test.
- Deliberately avoided introducing persistence or database access because no test required it yet.
- Returned a simple RegisterResponse built from the request data.

## Prompt

How should I evolve my registration feature from an in-memory implementation to persistence while following strict TDD?

## Summary

- Added a new business requirement that registration must persist users.
- Introduced a JPA User entity and Spring Data JPA repository.
- Switched the service to constructor injection.
- Used Mockito to verify repository interaction without connecting to PostgreSQL.
- Completed another RED → GREEN cycle before adding more features.

## Prompt

How should duplicate email registration be implemented while following strict Test Driven Development?

## Summary

- Added a new business requirement that email addresses must be unique.
- Wrote a failing unit test using Mockito to simulate an existing user.
- Added a derived query method (`existsByEmail`) to the repository.
- Implemented the minimum service logic to reject duplicate registrations.
- Completed another RED → GREEN cycle without introducing unnecessary infrastructure.

## Prompt

How should I refactor my registration service after completing the GREEN phase while following strict TDD and SOLID principles?

## Summary

- Extracted object conversion logic into a dedicated UserMapper.
- Simplified AuthService by removing DTO-to-Entity and Entity-to-DTO conversion code.
- Applied the Single Responsibility Principle without changing behavior.
- Completed the REFACTOR phase while keeping all tests passing.

## Prompt

How should password encoding be introduced into the registration workflow while following strict Test Driven Development?

## Summary

- Added a failing unit test requiring password encoding before persistence.
- Introduced PasswordEncoder as a constructor dependency.
- Used Mockito to verify that the password is encoded during registration.
- Kept encryption inside the service layer to preserve the mapper's single responsibility.

## Prompt

How should I replace generic exceptions with domain-specific exceptions while continuing to follow strict TDD?

## Summary

- Introduced EmailAlreadyExistsException for duplicate registrations.
- Updated the unit test first (RED).
- Replaced IllegalArgumentException in the service (GREEN).
- Prepared the service for centralized exception handling in future REST APIs.