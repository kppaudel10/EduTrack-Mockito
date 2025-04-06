# EduTrack-Mockito 🎓🧪

**EduTrack-Mockito** is a Spring Boot-based Student Management API that showcases best practices in layered architecture,
 unit testing with JUnit & Mockito, and environment configuration with YAML & ENV variables.

---

## 🚀 Features

- RESTful API to manage students
- PostgreSQL database integration
- Layered architecture:
  - Controller
  - Service
  - Component
  - Repository
  - Config with `@Bean`
- Mocked YAML & ENV properties using `@TestPropertySource`
- Unit & integration tests using:
  - JUnit 5
  - Mockito
  - Spring Boot Test
- >80% test coverage ready (with JaCoCo)

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- PostgreSQL
- Maven
- JPA / Hibernate
- JUnit 5
- Mockito
- JaCoCo (test coverage)

---

## ⚙️ Getting Started

### Prerequisites

- Java 21+
- Maven
- PostgreSQL running on `localhost:5432` with:
  - Database: `studentdb`
  - Username: `postgres`
  - Password: `postgres`

### Run the App

```bash
mvn spring-boot:run
