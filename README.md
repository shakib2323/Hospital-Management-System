# 🏥 Hospital Management System

A secure, enterprise-grade **Hospital Management System** built with **Java 21 & Spring Boot**, featuring JWT-based authentication, role-based access control, and a fully normalized relational database. Exposes 20+ RESTful APIs to manage hospitals, branches, departments, doctors, patients, appointments, prescriptions, billing, admissions, and audit tracking.

---

## 🔗 API Documentation

**Swagger UI:** [http://localhost:8099/HospitalManagementSystem/swagger-ui/index.html](http://localhost:8099/HospitalManagementSystem/swagger-ui/index.html#/)

---

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| ORM | JPA / Hibernate |
| Database | MySQL |
| Build Tool | Maven |
| Utilities | Lombok, MapStruct |
| API Docs | Swagger / OpenAPI |

---

## 🏗️ Architecture

```
HospitalManagementSystem
com.hms
├── controller       → REST API Endpoints
├── dto              → Request / Response DTOs
├── entity           → JPA Entity Models
├── repository       → Data Access Layer (Spring Data JPA)
├── service          → Business Logic Interfaces
├── service.impl     → Business Logic Implementation
├── mapper           → Entity ↔ DTO Conversion
├── security         → JWT Authentication & Authorization
├── audit            → Audit Logging & Entity Tracking
├── exception        → Global Exception Handling
├── response         → Standardized API Response Structure
├── enums            → Application Enums
├── config           → Swagger & App Configuration
└── util             → Utility Classes
```

---

## 🗄️ Database Setup

**Database:** MySQL  
**Credentials:** `root / root`  
**Schema:** `hospital_management_system`

```sql
CREATE DATABASE hospital_management_system;
USE hospital_management_system;
```

> The application uses JPA/Hibernate with `ddl-auto` — tables are created automatically on startup.

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+
- MySQL 8+
- Apache Tomcat 11

### Run the Application

```bash
# Clone the repository
git clone https://github.com/your-username/HospitalManagementSystem.git
cd HospitalManagementSystem

# Configure database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management_system
spring.datasource.username=root
spring.datasource.password=root

# Build and run
mvn clean install
mvn spring-boot:run
```

**Base URL:** `http://localhost:8099/HospitalManagementSystem/api/v1`

---

## 🔐 Authentication

All protected endpoints require a **JWT Bearer Token**.

### Login
```
POST /api/v1/auth/login
```
```json
{
  "username": "your_username",
  "password": "your_password"
}
```

Use the returned token in subsequent requests:
```
Authorization: Bearer <your_token>
```

---

## 👥 Roles & Permissions

| Role | Access |
|---|---|
| `ROLE_ADMIN` | Full system access |
| `ROLE_DOCTOR` | Patient, appointment, prescription management |
| `ROLE_RECEPTIONIST` | Appointment booking, patient registration |
| `ROLE_PATIENT` | View own records and appointments |

---

## 📋 Core Modules & APIs

| Module | Endpoints |
|---|---|
| Auth | Login, Logout |
| Hospital | Register, Update, Get, List |
| Branch | Create, Update, Get by Hospital |
| Department | Create, Assign to Branch |
| Doctor | Register, Update, Assign Specialization |
| Patient | Register, Update, Medical Records |
| Appointment | Book, Cancel, Update Status |
| Prescription | Create, Add Medicines |
| Billing | Generate Bill, View by Patient |
| Payment | Record Payment, View Status |
| Lab Test | Order Test, Upload Report |
| Admission | Admit Patient, Assign Bed, Discharge |
| Ambulance | Register, Update Availability |
| User Management | Create User, Assign Roles |
| Audit Log | View All Audit Records |

---

## 🗃️ Database Schema (28+ Tables)

```
hospital → branch → department → doctor → doctor_specialization
                              → room → bed → admission
users → user_roles → roles
patient → medical_record
        → appointment → prescription → prescription_medicine → medicine
        → lab_test → lab_report
        → billing → payment
pharmacy_stock → medicine
ambulance
audit_log
```

---

## ✅ Key Features

- **JWT Authentication** — Stateless token-based security
- **RBAC** — Role-specific endpoint access control
- **BCrypt Password Hashing** — Secure credential storage
- **Audit Logging** — Every entity creation/update/delete is tracked
- **Entity Metadata** — `createdBy`, `updatedBy`, `createdAt`, `updatedAt` on all entities
- **Global Exception Handling** — Standardized error responses across all APIs
- **Input Validation** — Jakarta Bean Validation on all request DTOs
- **Swagger UI** — Interactive API documentation and testing
- **Database Portability** — JPA abstraction supports MySQL, PostgreSQL, Oracle, SQL Server

---

## 📁 Project Structure

```
src/
└── main/
    ├── java/com/hms/
    │   ├── HospitalManagementSystemApplication.java
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/
    │   ├── repository/
    │   ├── service/
    │   │   └── impl/
    │   ├── mapper/
    │   ├── security/
    │   ├── audit/
    │   ├── exception/
    │   ├── response/
    │   ├── enums/
    │   ├── config/
    │   └── util/
    └── resources/
        └── application.properties
```

---

## 📄 License

This project is intended for educational and portfolio purposes.

---

> Built with ❤️ using Java 21 + Spring Boot
