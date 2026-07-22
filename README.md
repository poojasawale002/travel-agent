# ✈️ AI Travel Planner

AI Travel Planner is a Spring Boot REST API application that allows users to create, manage, and search travel plans.

## 🚀 Features

- Create Trip
- Get All Trips
- Get Trip By ID
- Update Trip
- Delete Trip
- Search by Source
- Search by Destination
- Search by Budget
- Sort by Budget (Ascending & Descending)
- Input Validation
- Global Exception Handling

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Postman
- Git & GitHub

## 📁 Project Structure

```
Controller
     ↓
Service
     ↓
Repository
     ↓
PostgreSQL
```

## 📌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /trip | Create Trip |
| GET | /trip | Get All Trips |
| GET | /trip/{id} | Get Trip By ID |
| PUT | /trip/{id} | Update Trip |
| DELETE | /trip/{id} | Delete Trip |
| GET | /trip/source?source=Mumbai | Search by Source |
| GET | /trip/destination?destination=Goa | Search by Destination |
| GET | /trip/budget?budget=5000 | Budget Less Than |
| GET | /trip/budget/greater?budget=5000 | Budget Greater Than |

## 👩‍💻 Author

**Pooja Sawale**