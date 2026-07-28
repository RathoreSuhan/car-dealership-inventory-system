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