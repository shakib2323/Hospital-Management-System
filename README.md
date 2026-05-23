# Hospital Management System

A secure, enterprise-grade Hospital Management System built with **Java 21** and **Spring Boot**, designed to support **multiple hospitals** from a single deployment. The system exposes 21+ RESTful APIs secured with JWT authentication and role-based access control, backed by a fully normalized relational database of 28+ tables.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Multi-Hospital Support](#multi-hospital-support)
- [Architecture](#architecture)
- [Database Schema](#database-schema)
- [Entity Classes](#entity-classes)
- [API Modules](#api-modules)
- [Roles and Permissions](#roles-and-permissions)
- [Getting Started](#getting-started)
- [Authentication](#authentication)
- [Key Features](#key-features)
- [Project Structure](#project-structure)
- [Author](#author)

---

## Overview

This system manages the complete operational lifecycle of a hospital network — including patient registration, doctor management, appointment booking, bed and room allocation, prescriptions, lab tests, billing, payments, ambulance tracking, and full audit logging. Built with clean layered architecture and production-grade security practices.

**Live API Docs (Swagger UI):**
`http://localhost:8099/HospitalManagementSystem/swagger-ui/index.html`

**Base URL:**
`http://localhost:8099/HospitalManagementSystem/api/v1`

---

## Tech Stack

| Layer          | Technology                        |
|----------------|-----------------------------------|
| Language       | Java 21                           |
| Framework      | Spring Boot 3.5                   |
| Security       | Spring Security + JWT             |
| ORM            | Spring Data JPA / Hibernate 6     |
| Database       | MySQL 8                           |
| Build Tool     | Maven                             |
| Utilities      | Lombok                 |
| API Docs       | Swagger / OpenAPI 3               |
| Server         | Apache Tomcat 11                  |

---

## Multi-Hospital Support

This system is architected to support **multiple hospitals** from a single deployment.

- Each **Hospital** is an independent entity with its own branches, departments, doctors, and patients.
- **Branches** belong to a specific hospital, allowing hospital networks to manage regional locations.
- **Departments** are scoped to branches, enabling fine-grained organizational structure per location.
- **Doctors** are assigned to specific branches and departments, preventing cross-hospital data leakage.
- **Patients, Appointments, Admissions, and Billing** are all linked through the hospital → branch hierarchy.
- A single Admin can manage the entire hospital network, while branch-level staff operate only within their scope.

This design allows the system to serve a hospital chain — for example, a network with a head office and multiple city branches — without deploying separate instances.

---

## Architecture

```
com.hms
├── controller          REST API endpoints
├── dto                 Request and Response DTOs
├── entity              JPA entity models
├── repository          Data access layer (Spring Data JPA)
├── service             Business logic interfaces
├── service/impl        Business logic implementation
├── mapper              Entity to DTO conversion (MapStruct)
├── security            JWT filter, authentication, authorization
├── audit               Audit logging and entity tracking
├── exception           Global exception handling
├── response            Standardized API response wrapper
├── enums               Application-wide enums
├── config              Swagger and application configuration
└── util                Utility classes
```

---

## Database Schema

The system uses **28 normalized relational tables** with proper foreign key constraints.

| Table                  | Description                                      |
|------------------------|--------------------------------------------------|
| `hospital`             | Root entity for each hospital in the network     |
| `branch`               | Hospital branches linked to a hospital           |
| `department`           | Departments within a branch                      |
| `specialization`       | Medical specializations available                |
| `roles`                | System roles (Admin, Doctor, etc.)               |
| `users`                | Authenticated system users                       |
| `user_roles`           | Many-to-many mapping of users to roles           |
| `doctor`               | Doctor profiles linked to branches               |
| `doctor_specialization`| Many-to-many mapping of doctors to specializations|
| `patient`              | Patient profiles and personal details            |
| `medical_record`       | Medical history linked to patients               |
| `appointment`          | Appointment bookings between patients and doctors|
| `prescription`         | Prescriptions issued after appointments          |
| `medicine`             | Medicine catalog                                 |
| `prescription_medicine`| Medicines assigned to prescriptions              |
| `pharmacy_stock`       | Stock levels of medicines per branch             |
| `room`                 | Rooms within a branch (ICU, General, Private)    |
| `bed`                  | Beds within rooms with availability tracking     |
| `admission`            | Patient admission and discharge records          |
| `billing`              | Bills generated for patient services             |
| `payment`              | Payment records linked to bills                  |
| `lab_test`             | Lab test orders for patients                     |
| `lab_report`           | Reports generated from lab tests                 |
| `ambulance`            | Ambulance fleet with availability status         |
| `audit_log`            | System-wide audit trail for all key operations   |
| `permission`           | Fine-grained permission definitions              |

**Schema Relationships:**

```
hospital
  └── branch
        ├── department
        │     └── doctor ──── doctor_specialization ──── specialization
        ├── room
        │     └── bed ──── admission ──── patient
        └── pharmacy_stock ──── medicine

users ──── user_roles ──── roles ──── permission

patient
  ├── medical_record
  ├── appointment ──── prescription ──── prescription_medicine ──── medicine
  ├── lab_test ──── lab_report
  ├── admission ──── bed ──── room ──── branch
  └── billing ──── payment

ambulance
audit_log
```

---

## Entity Classes

All 22 JPA entity classes under `com.hms.entity`:

| Entity Class          | Maps To Table            |
|-----------------------|--------------------------|
| `Hospital.java`       | `hospital`               |
| `Branch.java`         | `branch`                 |
| `Department.java`     | `department`             |
| `Specialization.java` | `specialization`         |
| `Role.java`           | `roles`                  |
| `User.java`           | `users`                  |
| `Doctor.java`         | `doctor`                 |
| `Patient.java`        | `patient`                |
| `MedicalRecord.java`  | `medical_record`         |
| `Appointment.java`    | `appointment`            |
| `Prescription.java`   | `prescription`           |
| `Medicine.java`       | `medicine`               |
| `PharmacyStock.java`  | `pharmacy_stock`         |
| `Room.java`           | `room`                   |
| `Bed.java`            | `bed`                    |
| `Admission.java`      | `admission`              |
| `Billing.java`        | `billing`                |
| `Payment.java`        | `payment`                |
| `LabTest.java`        | `lab_test`               |
| `LabReport.java`      | `lab_report`             |
| `Ambulance.java`      | `ambulance`              |
| `Permission.java`     | `permission`             |

---

## API Modules

The system exposes **21 controller classes** covering all modules:

| Controller                      | Base Path                        | Responsibility                              |
|---------------------------------|----------------------------------|---------------------------------------------|
| `AuthController`                | `/api/v1/auth`                   | Login, logout, token management             |
| `HospitalController`            | `/api/v1/hospitals`              | Register and manage hospitals               |
| `BranchController`              | `/api/v1/branches`               | Create and manage hospital branches         |
| `DepartmentController`          | `/api/v1/departments`            | Department creation and assignment          |
| `SpecializationController`      | `/api/v1/specializations`        | Manage medical specializations              |
| `DoctorController`              | `/api/v1/doctors`                | Doctor registration and specialization      |
| `PatientController`             | `/api/v1/patients`               | Patient registration and profile management |
| `MedicalRecordController`       | `/api/v1/medical-records`        | Patient medical history                     |
| `AppointmentController`         | `/api/v1/appointments`           | Book, cancel, and update appointments       |
| `PrescriptionController`        | `/api/v1/prescriptions`          | Issue and manage prescriptions              |
| `MedicineController`            | `/api/v1/medicines`              | Medicine catalog management                 |
| `PharmacyStockController`       | `/api/v1/pharmacy-stock`         | Pharmacy inventory per branch               |
| `RoomController`                | `/api/v1/rooms`                  | Room management within branches             |
| `BedController`                 | `/api/v1/beds`                   | Bed availability and assignment             |
| `AdmissionController`           | `/api/v1/admissions`             | Patient admission and discharge             |
| `BillingController`             | `/api/v1/billing`                | Generate and view patient bills             |
| `PaymentController`             | `/api/v1/payments`               | Record and track payments                   |
| `LabTestController`             | `/api/v1/lab-tests`              | Order and manage lab tests                  |
| `LabReportController`           | `/api/v1/lab-reports`            | Upload and retrieve lab reports             |
| `AmbulanceController`           | `/api/v1/ambulances`             | Ambulance fleet and availability            |
| `AuditLogController`            | `/api/v1/audit-logs`             | View system-wide audit records              |

---

## Roles and Permissions

| Role                  | Access Level                                                    |
|-----------------------|-----------------------------------------------------------------|
| `ROLE_ADMIN`          | Full system access — manage all hospitals, users, and data      |
| `ROLE_DOCTOR`         | Manage patients, appointments, prescriptions, and lab tests     |
| `ROLE_RECEPTIONIST`   | Patient registration, appointment booking                       |
| `ROLE_PATIENT`        | View own records, appointments, and bills                       |

**Authorization Rules:**

- `POST` endpoints — require authenticated users
- `PUT`, `PATCH`, `DELETE` endpoints — restricted by assigned role
- Admin role assignment — restricted to `ROLE_ADMIN` only (privilege escalation prevention)

---

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.8 or higher
- MySQL 8 or higher
- Apache Tomcat 11

### Database Setup

```sql
CREATE DATABASE hospital_management_system;
USE hospital_management_system;
```

Tables are created automatically via JPA/Hibernate `ddl-auto` on first startup.

### Installation

```bash
# Clone the repository
git clone https://github.com/shakib2323/Hospital-Management-System.git
cd Hospital-Management-System
```

Configure `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management_system
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

Access Swagger UI at:
`http://localhost:8099/HospitalManagementSystem/swagger-ui/index.html`

---

## Authentication

All protected endpoints require a JWT Bearer Token.

**Step 1 — Login:**

```
POST /api/v1/auth/login
```

```json
{
  "username": "your user name",
  "password": "your password"
}
```

**Step 2 — Use token in all subsequent requests:**

```
Authorization: Bearer <your-token>
```

---

## Key Features

- **Multi-Hospital Architecture** — Single deployment supports a full hospital network with isolated data per hospital and branch
- **JWT Authentication** — Stateless token-based security, no server-side session
- **Role-Based Access Control** — Endpoint-level permission enforcement per role
- **Privilege Escalation Prevention** — Admin role assignment restricted to Admin users only
- **BCrypt Password Hashing** — Secure credential storage using BCryptPasswordEncoder
- **Audit Logging** — Full audit trail for all critical system operations
- **Entity Metadata** — `createdBy`, `updatedBy`, `createdAt`, `updatedAt` on every entity
- **Global Exception Handling** — Standardized error response structure across all APIs
- **Input Validation** — Jakarta Bean Validation on all request DTOs
- **Swagger / OpenAPI** — Interactive API documentation and live testing
- **Database Portability** — JPA abstraction supports MySQL, PostgreSQL, Oracle, and SQL Server with minimal configuration change

---

## Project Structure

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

## Author

**Shakib Ali**
Java Full Stack Developer

---

## License

This project is intended for educational and portfolio purposes.
Licensed under the MIT License. Copyright 2026 Shakib Ali.
