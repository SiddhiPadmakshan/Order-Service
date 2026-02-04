# Order Service – Spring Boot Backend Application

A production-style backend service built using **Java 17** and **Spring Boot**, designed to manage orders with a complete lifecycle, validation, event publishing, and pagination support.

This project demonstrates real-world backend engineering practices including layered architecture, domain-driven design concepts, and clean separation of concerns.

---

## Features

- Create and manage orders
- Enforced order lifecycle transitions (PENDING → CONFIRMED → DISPATCHED → DELIVERED)
- Domain event publishing for:
  - Order creation
  - Order status changes
- Global exception handling with meaningful error responses
- Request & response DTOs
- Pagination and sorting support
- Input validation using Bean Validation (Jakarta Validation)
- Clean REST API design

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Postman (API testing)

---

## Project Architecture

The application follows a standard layered backend architecture:

controller → service → repository → database

Additional layers:
- `dto` – API contracts (request / response)
- `events` – domain event publishing
- `exception` – global error handling
- `model` – JPA entities and enums

This structure keeps business logic decoupled from infrastructure and presentation layers.

---

## API Endpoints

### Create Order

controller → service → repository → database

### Get All Orders (Paginated & Sorted)

GET /api/orders?page=0&size=5&sort=id,desc

### Get Order by ID

GET /api/orders/{id}

### Update Order Status

---

## Order Lifecycle Rules

- `PENDING` → `CONFIRMED` or `CANCELLED`
- `CONFIRMED` → `DISPATCHED` or `CANCELLED`
- `DISPATCHED` → `DELIVERED`
- Invalid transitions are rejected with clear error messages

---

## Domain Events

The service publishes domain-level events when:

- An order is created
- An order status changes

Example log output:

EVENT: OrderStatusChanged | orderId=3 | CONFIRMED → DISPATCHED | at=2025-12-31T12:20:26Z

This design prepares the system for asynchronous processing (e.g. Kafka integration).

---

## Running the Application

### Prerequisites
- Java 17+
- Maven
- PostgreSQL running locally

### Steps
```bash
mvn spring-boot:run

The service will start on: http://localhost:8081
```

## Testing

APIs were tested using Postman.
Validation and error scenarios are handled centrally via a global exception handler.

## Future Improvements
- Kafka integration for real event streaming
- CI/CD pipeline
- Unit and integration tests
- Swagger / OpenAPI documentation
- Docker support