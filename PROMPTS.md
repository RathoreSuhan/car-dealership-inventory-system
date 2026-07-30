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

## Prompt

How should I implement the vehicle restock feature while following the Red-Green-Refactor TDD cycle and SOLID principles?

## AI Assistance Summary

### Goal

Implement inventory restocking with proper validation and clean service-layer design.

### Guidance Received

- Added a failing unit test before implementing the feature.
- Implemented restock logic by increasing the vehicle quantity.
- Added a REST endpoint for restocking inventory.
- Refactored the service by introducing a dedicated exception to reject invalid restock quantities.
- Kept validation within the service layer to preserve separation of concerns.


## Prompt

Implement vehicle deletion using Test-Driven Development while following SOLID principles.

## AI Assistance Summary

### Goal

Implement the minimum production code required to satisfy the failing delete vehicle test.

### Guidance Received

- Added deleteVehicle() in VehicleService.
- Retrieved the entity before deletion to ensure proper exception handling.
- Added DELETE endpoint returning HTTP 204 No Content.
- Kept the implementation minimal to satisfy the GREEN phase before refactoring.

## Prompt

Refactor the VehicleService tests after completing multiple TDD cycles while preserving behavior and following SOLID principles.

## AI Assistance Summary

### Goal

Improve test readability and maintainability without changing application behavior.

### Guidance Received

- Replaced manual mock creation with @Mock and @InjectMocks.
- Added @BeforeEach for centralized Mockito initialization.
- Extracted reusable helper methods for test data creation.
- Removed duplicated setup code while preserving all existing test behavior.


## Prompt

How can I implement role-based authorization using Spring Security while continuing to follow the Red-Green-Refactor TDD cycle?

## AI Assistance Summary

### Goal

Begin implementing role-based authorization for protected vehicle endpoints.

### Guidance Received

- Added a security integration test using MockMvc.
- Used @WithMockUser to simulate authenticated users.
- Wrote the first failing authorization test before changing production code.
- Prepared the project for implementing admin-only endpoint protection.


## Prompt

How should I enforce role-based authorization for vehicle management APIs using Spring Security while following the Green phase of TDD?

## AI Assistance Summary

### Goal

Implement role-based access control for vehicle management endpoints.

### Guidance Received

- Updated SecurityConfig to define role-specific authorization rules.
- Restricted create, update, delete, and restock endpoints to ADMIN users.
- Allowed authenticated users to browse, search, and purchase vehicles.
- Verified the implementation with integration tests for both USER and ADMIN roles.

## Prompt

Refactor the VehicleService to remove duplicated repository lookup logic while preserving behavior and following SOLID principles.

## AI Assistance Summary

### Goal

Improve maintainability by eliminating repeated vehicle lookup code.

### Guidance Received

- Extracted a private helper method for fetching a vehicle by ID.
- Replaced duplicated lookup code in update, purchase, restock, and delete operations.
- Preserved existing behavior while improving readability and reducing duplication.



## Prompt

How should I implement a unified vehicle search endpoint using Test-Driven Development while following SOLID principles?

## AI Assistance Summary

### Goal

Introduce a single search endpoint capable of filtering vehicles by make, model, category, and price range.

### Guidance Received

- Started with a failing repository test.
- Planned to implement the search using JPQL with optional parameters.
- Chose a single endpoint instead of multiple dedicated search endpoints.
- Followed the RED phase before modifying production code.


## Prompt

Implement a unified vehicle search endpoint using JPQL while following the Green phase of Test-Driven Development.

## AI Assistance Summary

### Goal

Implement a single vehicle search endpoint supporting optional filters.

### Guidance Received

- Added a JPQL query with optional parameters.
- Introduced a unified service method for searching vehicles.
- Exposed a single REST endpoint for searching by make, model, category, and price range.
- Verified the implementation by running the complete test suite successfully.


## Prompt

Refactor the unified vehicle search API to improve maintainability while preserving existing behavior.

## AI Assistance Summary

### Goal

Improve the controller design by encapsulating search filters.

### Guidance Received

- Introduced a dedicated VehicleSearchRequest DTO.
- Replaced multiple request parameters with a single model attribute.
- Preserved the REST API contract while improving readability and maintainability.


## Prompt

Create integration tests for the vehicle REST APIs using MockMvc and Spring Boot Test while preserving the layered architecture.

## AI Assistance Summary

### Goal

Verify the complete request lifecycle from controller to database.

### Guidance Received

- Used SpringBootTest with MockMvc.
- Tested HTTP endpoints instead of individual methods.
- Validated request mapping, persistence, and response status.
- Complemented existing unit tests with integration tests.


## Prompt

Fix test suite failures in `VehicleControllerSecurityTest` caused by security role misconfigurations, duplicate records during test execution, and unauthenticated status code mismatches.

## AI Assistance Summary

### Goal

Ensure the security test suite passes cleanly without polluting the local database or producing duplicate entries.

### Guidance Received

- Updated test expectations to match security policy changes for vehicle creation endpoints.
- Isolated test database operations by configuring an in-memory H2 database under `src/test/resources/application.properties`.
- Added `@Transactional` support to test classes to ensure rollback after each test execution.
- Resolved configuration conflicts by separating local development settings from the test environment.


---

# ============================================================
#                     FRONTEND DEVELOPMENT
# ============================================================

---


## Phase 1 - React + Vite Setup

Completed:
- Created React project using Vite
- Installed project dependencies
- Verified development server
- Removed default Vite starter content
- Simplified App.jsx to a clean starting point



## Prompt

Implement JWT authentication in the React frontend by integrating the existing Spring Boot authentication APIs. Centralize authentication using React Context, create protected routes, and persist the JWT across page refreshes.

## AI Assistance Summary

### Goal

Build a reusable authentication layer for the React application that works with the existing Spring Security JWT backend.

### Guidance Received

- Created Login and Register pages connected to the backend authentication endpoints.
- Configured AuthContext to manage login, logout, authentication state, and JWT persistence.
- Added a reusable `useAuth` custom hook for accessing authentication state throughout the application.
- Implemented a `PrivateRoute` component to protect authenticated pages.
- Configured Axios to automatically attach the JWT in the `Authorization` header for every protected API request.
- Redirected users appropriately after successful login and registration.


## Prompt

Build the initial React dashboard for the car dealership inventory system by integrating the existing Spring Boot vehicle APIs. Create reusable UI components, display vehicles in a responsive grid, support vehicle purchases, and include loading and error handling states.

## AI Assistance Summary

### Goal

Develop the first functional dashboard that consumes backend APIs and displays the vehicle inventory in a clean, reusable React architecture.

### Guidance Received

- Created reusable `Navbar` and `VehicleCard` components.
- Added a `Dashboard` page that fetches vehicles using Axios.
- Integrated the purchase API and refreshed inventory after successful purchases.
- Added loading indicators and toast notifications for better user feedback.
- Updated the vehicle service to return API payloads directly for simpler component logic.


## Prompt

Implement a searchable vehicle dashboard in the React frontend using the existing Spring Boot search endpoint.

## AI Assistance Summary

### Goal

Allow users to filter vehicles by make, model, category, and price range without changing the backend API.

### Guidance Received

- Created a reusable `SearchBar` component for collecting filter criteria.
- Connected the dashboard to the `/api/vehicles/search` endpoint through the existing service layer.
- Fixed Axios response handling by using `response.data` instead of the full response object.
- Kept the search UI independent from the vehicle card component for better reusability.


## Prompt

Build the frontend admin module for vehicle inventory management using the existing Spring Boot CRUD APIs.

## AI Assistance Summary

### Goal

Create a reusable admin interface for managing vehicles without changing the backend API contract.

### Guidance Received

- Planned a dedicated admin dashboard for inventory management.
- Designed reusable components for vehicle forms, tables, and confirmation dialogs.
- Reused the existing Axios service layer for create, update, delete, and restock operations.
- Structured the UI to keep business logic inside pages and presentation logic inside reusable components.


## Prompt

Build a reusable vehicle form for the admin module using the existing Spring Boot validation rules and create API.

## AI Assistance Summary

### Goal

Implement a reusable form component that can be shared between vehicle creation and editing while matching backend validation.

### Guidance Received

- Created a reusable `VehicleForm` component with React Hook Form.
- Mirrored Spring Boot validation constraints (`@NotBlank`, `@Positive`, `@PositiveOrZero`) on the frontend.
- Built the initial `AdminDashboard` page using the reusable form.
- Connected the form to the existing `addVehicle` service without changing the backend API.


## Prompt

Build a reusable vehicle table for the admin dashboard to display inventory and prepare it for CRUD operations.

## AI Assistance Summary

### Goal

Create a reusable table component that displays vehicle inventory and exposes action callbacks for edit, delete, and restock.

### Guidance Received

- Built a reusable `VehicleTable` component for inventory display.
- Added configurable callbacks for edit, delete, and restock actions.
- Kept the component presentation-focused while delegating business logic to the admin page.
- Structured the table for easy integration with existing Spring Boot CRUD APIs.


## Prompt

Integrate the admin dashboard with the backend vehicle APIs to display inventory and refresh it after vehicle creation.

## AI Assistance Summary

### Goal

Create a functional admin dashboard that loads vehicles from the backend and updates the inventory after adding a new vehicle.

### Guidance Received

- Connected the admin dashboard to the existing `getVehicles` and `addVehicle` service methods.
- Added a reusable inventory table to display backend data.
- Refreshed the vehicle list automatically after successful vehicle creation.
- Added placeholders for edit, delete, and restock operations to prepare the remaining CRUD features.