# ✈️ Travel Agent - Spring Boot REST API

A secure Travel Agent REST API built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, and **PostgreSQL**. This project demonstrates enterprise-level backend development concepts including authentication, authorization, DTO mapping, pagination, sorting, validation, exception handling, logging, and API documentation.

---

# 🚀 Features

- User Registration
- User Login with JWT Authentication
- Role-Based Authorization (ADMIN / USER)
- CRUD Operations for Trips
- Pagination
- Sorting
- JPQL Queries
- DTO Pattern
- ModelMapper
- Bean Validation
- Global Exception Handling
- Swagger/OpenAPI Documentation
- Spring Boot Actuator
- Logging with SLF4J
- PostgreSQL Database
- RESTful API Design

---

# 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21+ |
| Spring Boot | 3.x |
| Spring Security | 6.x |
| Spring Data JPA | Latest |
| JWT | JJWT |
| PostgreSQL | 16+ |
| Maven | Latest |
| Hibernate | Latest |
| ModelMapper | Latest |
| Swagger OpenAPI | springdoc-openapi |
| Lombok | Optional |

---

# 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── jwt
 ├── security
 ├── config
 ├── exception
 ├── util
 └── resources
```

---

# 🗄 Database

PostgreSQL

Main Tables

- trip
- trip_user
- roles
- user_roles

---

# 🔐 Authentication

The application uses JWT Authentication.

### Register User

POST

```
/tripusers
```

### Login

POST

```
/auth/login
```

Response

```json
{
  "token":"YOUR_JWT_TOKEN"
}
```

Use the token in every protected request.

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 👤 Roles

## ADMIN

Can

- Create Trip
- Update Trip
- Delete Trip
- View Trips

## USER

Can

- View Trips
- Search Trips

---

# 📌 API Endpoints

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | /tripusers |
| POST | /auth/login |

---

## Trip APIs

| Method | Endpoint |
|---------|----------|
| POST | /trip |
| GET | /trip/{id} |
| GET | /trip/page |
| GET | /trip/page-sort |
| PUT | /trip/{id} |
| PATCH | /trip/{id}/budget |
| DELETE | /trip/{id} |

---

## Search APIs

| Method | Endpoint |
|---------|----------|
| GET | /trip/source |
| GET | /trip/destination |
| GET | /trip/budget |
| GET | /trip/budget/greater |
| GET | /trip/search |
| GET | /trip/search/or |
| GET | /trip/destination/start |
| GET | /trip/destination/end |

---

## JPQL APIs

| Method | Endpoint |
|---------|----------|
| GET | /trip/jpql/source |
| GET | /trip/jpql/budget |
| GET | /trip/jpql/destination |

---

# 📄 API Documentation

Swagger UI

```
http://localhost:8081/swagger-ui/index.html
```

---

# ❤️ Spring Boot Actuator

Health Endpoint

```
http://localhost:8081/actuator/health
```

---

# ▶️ Running the Project

## Clone Repository

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/travel-agent.git
```

---

## Navigate

```bash
cd travel-agent
```

---

## Configure Database

Update

```
application.properties
```

Example

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/travelagent

spring.datasource.username=postgres

spring.datasource.password=yourpassword
```

---

## Run

```bash
mvn spring-boot:run
```

or

Run

```
TravelAgentApplication.java
```

---

# 🧪 Testing

Swagger

```
http://localhost:8081/swagger-ui/index.html
```

Postman Collection can also be used.

---

# 📷 Screenshots

## Swagger

(Add Screenshot Here)

---

## Login

(Add Screenshot Here)

---

## Create Trip

(Add Screenshot Here)

---

## PostgreSQL Tables

(Add Screenshot Here)

---

# 📈 Future Enhancements

- Unit Testing (JUnit + Mockito)
- Docker
- Docker Compose
- CI/CD using GitHub Actions
- AWS Deployment
- Redis Caching
- Email Notifications

---

# 👩‍💻 Author

**Pooja Sawale**

Backend Java Developer

GitHub:
https://github.com/poojasawale002

LinkedIn:
https://www.linkedin.com/in/pooja-sawale/

---

# ⭐ If you like this project

Please give it a ⭐ on GitHub.