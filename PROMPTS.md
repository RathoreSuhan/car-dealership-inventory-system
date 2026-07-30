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

## Prompt

How should I implement JWT authentication in a Spring Boot application while following Test-Driven Development and keeping the security layer clean and maintainable?

## AI Assistance Summary

### Goal

Implement JWT-based authentication after completing user registration and login while following the Red → Green → Refactor workflow.

### Guidance Received

- Planned the authentication module before implementation.
- Added JWT library dependencies.
- Extended the User entity with a role field.
- Assigned the default `USER` role during registration.
- Planned security components including JwtService, JwtAuthenticationFilter, SecurityConfig, and CustomUserDetailsService.
- Chose to implement JWT incrementally instead of mixing authentication and business logic.
- Kept authentication concerns separated from service-layer business logic following SOLID principles.

## Prompt

How should I integrate Spring Security's UserDetails into my authentication module while continuing to follow Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Integrate Spring Security's authentication model with the existing User entity before implementing JWT validation and protected endpoints.

### Guidance Received

- Updated the User entity to implement the UserDetails interface.
- Implemented all required UserDetails methods with explanatory comments.
- Refactored JwtService to use UserDetails instead of directly depending on the User entity.
- Preserved existing unit tests through polymorphism.
- Applied the Dependency Inversion Principle by depending on the UserDetails abstraction rather than a concrete entity.

## Prompt

How should I integrate Spring Security with my PostgreSQL UserRepository while following Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Allow Spring Security to load users from the database before implementing JWT authentication filters.

### Guidance Received

- Created unit tests for CustomUserDetailsService before implementation.
- Implemented Spring Security's UserDetailsService interface.
- Loaded users from PostgreSQL using the UserRepository.
- Used the existing User entity as the UserDetails implementation.
- Applied the Dependency Inversion Principle by allowing authentication to depend on the UserDetailsService abstraction instead of directly accessing the repository.


## Prompt

How should I introduce a JWT authentication filter in Spring Boot while following Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Introduce the JWT authentication filter before implementing token validation.

### Guidance Received

- Created a failing unit test before implementing the filter.
- Implemented JwtAuthenticationFilter by extending OncePerRequestFilter.
- Allowed all requests to continue through the filter chain while postponing JWT validation to the next iteration.
- Applied the Single Responsibility Principle by separating filter creation from authentication logic.

## Prompt

How should I authenticate incoming HTTP requests using JwtAuthenticationFilter while following Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Authenticate requests carrying JWT tokens before implementing authorization rules.

### Guidance Received

- Updated JwtAuthenticationFilter to extract JWTs from the Authorization header.
- Integrated JwtService and CustomUserDetailsService for user authentication.
- Added username extraction support in JwtService.
- Stored authenticated users inside Spring Security's SecurityContextHolder.
- Continued following the Red → Green workflow while keeping JWT parsing separate from validation logic.


## Prompt

How should I validate JWT tokens before authenticating incoming requests while following Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Validate JWT tokens before creating an authenticated SecurityContext.

### Guidance Received

- Updated JwtAuthenticationFilter to authenticate only after validating the JWT.
- Added token validation methods to JwtService.
- Verified username matching and token expiration.
- Reused JWT signature verification provided by the JJWT parser.
- Kept authentication stateless by relying on JWT instead of rechecking user credentials.


## Prompt

How should I configure Spring Security using SecurityFilterChain for a stateless JWT authentication system while following SOLID principles?

## AI Assistance Summary

### Goal

Configure Spring Security to use JWT authentication for protected REST APIs.

### Guidance Received

- Created a dedicated SecurityConfig class.
- Configured stateless session management.
- Registered JwtAuthenticationFilter before Spring Security's default authentication filter.
- Allowed unauthenticated access only to authentication endpoints.
- Exposed AuthenticationManager for future authentication refactoring.


## Prompt

How should I begin implementing the Vehicle module while continuing to follow Test-Driven Development and SOLID principles?

## AI Assistance Summary

### Goal

Start the Vehicle module by defining the domain model and creating the first failing service test.

### Guidance Received

- Designed the Vehicle entity and persistence layer.
- Created request/response DTOs.
- Added a mapper to isolate object conversion.
- Wrote the first failing unit test for vehicle creation before implementing the service.
- Continued following the Red-Green-Refactor workflow.


## Prompt

How should I implement the Vehicle module in a Spring Boot REST API while continuing to follow SOLID principles and practical TDD?

## AI Assistance Summary

### Goal

Implement the core Vehicle management functionality required by the project.

### Guidance Received

- Implemented the Vehicle service and controller.
- Added APIs to create and list vehicles.
- Added search functionality by make, model, and category.
- Protected vehicle APIs with JWT authentication.
- Continued using DTOs, mappers, and repository abstractions to keep the design clean.


## Prompt

How should I implement the update vehicle feature while continuing to follow Red-Green-Refactor TDD and SOLID principles?

## AI Assistance Summary

### Goal

Implement the vehicle update workflow with proper TDD and domain-driven exception handling.

### Guidance Received

- Wrote the failing service-layer test before implementation.
- Implemented the update business logic in the service layer.
- Added the update REST endpoint.
- Replaced a generic RuntimeException with a VehicleNotFoundException during the refactor phase.
- Preserved clean separation between controller, service, repository, DTO, and mapper layers.

## Prompt

How should I implement the vehicle purchase feature while following Red-Green-Refactor TDD and SOLID principles?

## AI Assistance Summary

### Goal

Implement the purchase workflow while ensuring inventory cannot become negative.

### Guidance Received

- Added a failing unit test before implementation.
- Implemented purchase logic by decreasing stock by one.
- Exposed the purchase endpoint through the controller.
- Refactored the service by introducing an OutOfStockException to enforce inventory constraints.