# SwimApp

SwimApp is a full-stack swimming management system for managing swimmers, parents, trainers, courses, levels, and swimming requirements.

The project is built as a learning/portfolio project with a focus on a modern full-stack architecture, authentication, role-based access control, and CRUD management.

## Tech Stack

### Frontend

* Angular 20
* Angular Material
* TypeScript
* Standalone components
* Angular Signals
* Reactive Forms

### Backend

* Quarkus
* Java
* REST API
* PostgreSQL
* Keycloak
* OpenID Connect / OAuth 2.0

### Infrastructure

* Docker
* Docker Compose
* PostgreSQL

---

## Main Features

The system is designed around different user roles.

### Admin

Administrators can manage:

* Swimmers
* Trainers
* Courses
* Levels
* Level requirements

The admin dashboard provides an overview of the swimming school and quick access to management functions.

### Parent

Parents can:

* View their profile
* Register swimmers
* View their swimmers
* View swimmer/course information

### Trainer

Trainers can:

* View their profile
* View their assigned courses
* Manage trainer-related information

---

## Architecture

The application follows a typical full-stack architecture:

```text
Angular Frontend
       │
       │ HTTP / JSON
       ▼
Quarkus REST API
       │
       │ JDBC / Hibernate ORM
       ▼
SwimApp PostgreSQL

       ▲
       │
       │ Authentication
       │
    Keycloak
       │
       ▼
Keycloak PostgreSQL
```

The application database and Keycloak database are intentionally separated.

---

# Getting Started

## Prerequisites

Install the following before running the project:

* Java 21+
* Node.js
* npm
* Angular CLI
* Docker
* Docker Compose

You can check your installations with:

```bash
java -version
node -v
npm -v
docker --version
docker compose version
```

---

# Project Structure

A simplified project structure looks like this:

```text
SwimApp/
│
├── frontend/
│   └── Angular application
│
├── backend/
│   └── Quarkus application
│
├── keycloak/
│   └── realm/
│       └── realm configuration
│
├── docker-compose.yml
│
└── README.md
```

The exact folder names may differ depending on your local project structure.

---

# Docker Compose

Docker Compose provides the databases and Keycloak required by the application.

The application PostgreSQL database is separate from the PostgreSQL database used by Keycloak.

```yaml
services:

  # --------------------------------------------------
  # SwimApp PostgreSQL database
  # --------------------------------------------------
  postgres-app:
    image: postgres:18
    container_name: swimapp-postgres
    environment:
      POSTGRES_USER: swimapp
      POSTGRES_PASSWORD: swimapp
      POSTGRES_DB: swimapp
    volumes:
      - pgdata_app:/var/lib/postgresql
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U swimapp -d swimapp"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 10s


  # --------------------------------------------------
  # Keycloak PostgreSQL database
  # --------------------------------------------------
  postgres-keycloak:
    image: postgres:18
    container_name: postgres-keycloak
    environment:
      POSTGRES_USER: keycloak
      POSTGRES_PASSWORD: keycloak
      POSTGRES_DB: keycloak
    volumes:
      - pgdata_keycloak:/var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U keycloak -d keycloak"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s


  # --------------------------------------------------
  # Keycloak
  # --------------------------------------------------
  keycloak:
    image: keycloak/keycloak:latest
    container_name: swimapp-keycloak
    command: start-dev --import-realm
    environment:
      KC_BOOTSTRAP_ADMIN_USERNAME: admin
      KC_BOOTSTRAP_ADMIN_PASSWORD: admin

      KC_DB: postgres
      KC_DB_URL: jdbc:postgresql://postgres-keycloak:5432/keycloak
      KC_DB_USERNAME: keycloak
      KC_DB_PASSWORD: keycloak

      KC_HEALTH_ENABLED: "true"
      KC_HOSTNAME: http://localhost:8081
      KC_HOSTNAME_BACKCHANNEL_DYNAMIC: "true"

    ports:
      - "8081:8080"

    depends_on:
      postgres-keycloak:
        condition: service_healthy

    volumes:
      - ./keycloak/realm:/opt/keycloak/data/import

    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9000/health/ready || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 30
      start_period: 30s


volumes:

  pgdata_app:

  pgdata_keycloak:
```

## Start Docker Services

From the project root:

```bash
docker compose up -d
```

Check the running containers:

```bash
docker compose ps
```

You should see:

```text
swimapp-postgres
postgres-keycloak
swimapp-keycloak
```

To stop the services:

```bash
docker compose down
```

To stop the services and remove the database volumes:

```bash
docker compose down -v
```

**Warning:** `docker compose down -v` deletes the PostgreSQL data stored in the Docker volumes.

---

# Database Configuration

The SwimApp application database is:

```text
Host: localhost
Port: 5432
Database: swimapp
Username: swimapp
Password: swimapp
```

The Keycloak database is intentionally separate:

```text
Host: postgres-keycloak
Port: 5432
Database: keycloak
Username: keycloak
Password: keycloak
```

The Quarkus application connects to the `swimapp` database.

For example, the Quarkus configuration can contain:

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=swimapp
quarkus.datasource.password=swimapp
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/swimapp
```

Adjust the configuration according to the actual configuration used by the project.

---

# Keycloak

Keycloak is used for authentication and authorization.

The development Keycloak instance is available at:

```text
http://localhost:8081
```

The development administrator credentials configured by Docker Compose are:

```text
Username: admin
Password: admin
```

These credentials are intended for local development only.

The project imports the Keycloak realm automatically from:

```text
keycloak/realm/
```

The imported realm contains the users, roles, clients, and other authentication configuration required by the application.

---

# Running the Backend

Navigate to the Quarkus backend:

```bash
cd backend
```

Run the application in development mode:

```bash
./mvnw quarkus:dev
```

On Windows:

```bash
mvnw.cmd quarkus:dev
```

The backend should then be available at:

```text
http://localhost:8080
```

---

# Running the Frontend

Navigate to the Angular application:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start Angular:

```bash
ng serve
```

The frontend should be available at:

```text
http://localhost:4200
```

---

# Running the Complete Application

A typical development startup sequence is:

### 1. Start Docker services

```bash
docker compose up -d
```

### 2. Start the Quarkus backend

```bash
cd backend
./mvnw quarkus:dev
```

### 3. Start the Angular frontend

In another terminal:

```bash
cd frontend
npm install
ng serve
```

Then open:

```text
http://localhost:4200
```

---

# Development Notes

This project is currently under active development.

Some areas may still be simplified or incomplete, including:

* Dashboard layout
* Role-specific dashboards
* Course management
* Swimmer management
* Level management
* Trainer management
* Pagination
* Validation
* Error handling
* Production configuration

The project is intentionally being developed incrementally.

---

# Future Improvements

Possible future improvements include:

* Separate dashboards for Admin, Parent, and Trainer
* Course enrollment management
* Swimmer-to-level assignment
* Swimmer-to-course assignment
* Trainer scheduling
* Advanced search and filtering
* Pagination
* Improved error handling
* Audit logging
* Automated tests
* Production Docker configuration
* Environment-specific configuration
* CI/CD pipeline
* Production security configuration

---

# License

This project is currently intended as a personal learning project
