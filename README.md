# Order Service – Spring Boot Backend Application

A production-style backend service built with Java 17 and Spring Boot, designed to manage orders with clear business rules, lifecycle validation, event publishing, and paginated APIs.

This project demonstrates clean backend architecture, domain-driven thinking, and real-world API design, leaving room for future scalability (event-driven systems, Kafka, CI/CD).

🚀 Features
✅ Order Management

Create orders with validation

Retrieve all orders

Retrieve orders with pagination and sorting

Update order status with strict lifecycle rules

🔁 Order Lifecycle Enforcement

Valid state transitions are enforced at the business logic level:

PENDING → CONFIRMED → DISPATCHED → DELIVERED


Invalid transitions are blocked with meaningful error responses.

📣 Event Publishing (Level 1 – In-Process)

Domain events are published when:

An order is created

An order status changes

Example logs:

EVENT: OrderCreated | orderId=7 | product=iPhone 15 | quantity=2 | amount=1.0
EVENT: OrderStatusChanged | orderId=3 | CONFIRMED → DISPATCHED


This prepares the service for future event-driven architectures (Kafka, messaging systems).

📄 Pagination & Sorting

The API supports paginated and sorted responses using Spring Data:

GET /api/orders?page=0&size=5&sort=id,desc


Returns structured metadata:

total pages

total elements

current page

sorted content

🛑 Robust Error Handling

Centralised exception handling using @RestControllerAdvice

Clean, client-friendly error responses

Validation errors returned as field-level messages

Example:

{
  "productName": "Product name is required",
  "quantity": "Quantity must be at least 1"
}

🛠️ Tech Stack

Backend

Java 17

Spring Boot 3.x

Spring Data JPA

Hibernate

Database

PostgreSQL

Validation & APIs

Jakarta Validation

RESTful API design

DTO-based request/response separation

Tooling

Maven

Postman

Git / GitHub

🧱 Project Structure
src/main/java/com/siddhi/orderservice
│
├── controller        # REST API endpoints
├── service           # Business logic & rules
├── repository        # Data access (JPA)
├── model             # Entities & enums
├── dto               # Request & response DTOs
├── events             # Domain events & publishers
├── exception         # Custom exceptions & handlers
└── config            # Application configuration

📌 Key Design Decisions

DTOs used to decouple API contracts from persistence models

Business rules enforced in the service layer (not controllers)

Events introduced early to support scalability

Pagination used to protect APIs from large datasets

Centralised error handling for maintainability

🔮 Planned Enhancements

Kafka-based event publishing (Level 1)

CI/CD pipeline (build + test)

API documentation (OpenAPI / Swagger)

Security (role-based access)

🧠 What This Project Demonstrates

Backend engineering fundamentals

Real-world Spring Boot patterns

Clean separation of concerns

Production-style error handling

Readiness for distributed systems

▶️ Running the Application
mvn spring-boot:run


The API will be available at:

http://localhost:8080

📬 Sample Endpoints
POST    /api/orders
GET     /api/orders
GET     /api/orders?page=0&size=5&sort=id,desc
PUT     /api/orders/{id}/status

