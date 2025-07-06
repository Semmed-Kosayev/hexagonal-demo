# Spring boot Micro services (monorepo)
## Microservices sample project with hexagonal and DDD approach
### by Forever

# 🧱 Product Service

This is a microservice built with **Spring Boot**, using **Hexagonal Architecture** and **Domain-Driven Design (DDD)** principles.

It manages product creation and modification. It stores data in **PostgreSQL** and uses **Docker Compose** to spin up the database.

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL (via Docker Compose)
- Hexagonal Architecture
- Domain-Driven Design (DDD)
- Gradle (Groovy DSL)

---

## 📁 Package Structure

product-service\
├── adapter\
│ ├── in # REST controller (inbound adapter)\
│ └── out\
│ └── persistence # JPA implementation (outbound adapter)\
├── application # Use-case implementations\
├── domain\
│ ├── model # Aggregate + Value Objects\
│ ├── port\
│ │ ├── in # Use-case interfaces\
│ │ └── out # Repository interfaces\
├── configuration # Spring Boot setup\
└── ProductServiceApplication.java\

## 🚀 How to Run

### 1. Start PostgreSQL via Docker Compose

From the root `hexagonal-demo/` folder:

```bash
docker-compose up -d
```
This will start a PostgreSQL container with:

DB: productdb

User: product_user

Password: product_pass

### 2. Start Product-Service
```bash
cd product-service
./gradlew bootRun
```
App will start on:\
📍 http://localhost:8081