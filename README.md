# Todo Service API: An "Operating a Service" Showcase

[![Java CI with Maven](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml/badge.svg)](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml)

## 1. Project Vision 🚀

This project documents the evolution of a standard Spring Boot CRUD application into a production-ready, observable, and secure RESTful service. The primary goal is to demonstrate best practices in **operating a cloud-native application** on Google Cloud Platform, focusing on security, performance analysis, and reliability.

This repository serves as a practical showcase of the skills required to not just *build* an application, but to *run and maintain* it effectively in a modern cloud environment.

-----

## 2. Key Features ✅

The application has been enhanced with several production-grade features, all of which are fully functional in the local development environment.

* **Secure, Token-Based Authentication:** The entire API is now protected using Spring Security and JSON Web Tokens (JWT). Public endpoints are provided for user registration and login.
* **Full CRUD Functionality:** All create, read, update, and delete operations for tasks are secured and functional.
* **Scalable API Pagination:** The `GET /api/todo-tasks` endpoint now supports pagination, allowing for efficient data retrieval with `page` and `size` parameters.
* **Input Validation & Error Handling:** The API uses a global error handler to provide clean, predictable responses for invalid requests.
* **Containerization:** The application and its PostgreSQL database are fully containerized using Docker and can be run locally with a single `docker-compose` command.
* **Continuous Integration (CI):** A GitHub Actions workflow automatically builds and runs a full suite of tests on every push.

-----

## 3. The Next Phase: Project Evolution Roadmap 🗺️

The next phase of this project is to complete the service hardening and conduct a rigorous performance analysis.

### Phase 1: Service Hardening
- [ ] **Integrate with Cloud SQL:** Re-attempt the deployment to connect the secure service to a persistent PostgreSQL instance on Google Cloud SQL.
- [x] **Implement API Pagination:** 
- [x] **Secure Endpoints with JWT:** 
- [ ] **Add Rate Limiting:** Introduce a basic rate-limiting mechanism to the API.

### Phase 2: Performance Analysis & Reporting
- [ ] **Create `perf.md` Report:** Establish a markdown file to document the performance testing methodology and results.
- [ ] **Execute k6 Test Matrix:** Run load tests against a 2x2 matrix of Cloud Run configurations.
- [ ] **Analyze & Document:** Capture latency (p50/p95/p99) and cold-start data from Cloud Monitoring.

-----

## 4. Tech Stack 🛠️

* **Backend:** Java 17, Spring Boot 3, Spring Security (JWT)
* **Database:** PostgreSQL, Google Cloud SQL, H2
* **Testing:** JUnit 5, MockMvc
* **CI/CD:** GitHub Actions, Google Cloud Build, Google Artifact Registry
* **Cloud Platform:** Google Cloud Run, Google Cloud Monitoring
* **Performance Testing:** k6

-----

## 5. Local Development & Usage

The project is fully runnable and testable on a local machine.

* **Run the full stack locally:** `docker-compose up -d --build`
* **Run all tests:** `mvn verify`

### Using the Secure API

1.  **Register a new user:**
    ```bash
    curl -X POST http://localhost:8080/api/auth/register \
    -H "Content-Type: application/json" \
    -d '{"username": "testuser", "password": "password123"}'
    ```

2.  **Login to get a JWT:**
    ```bash
    curl -X POST http://localhost:8080/api/auth/login \
    -H "Content-Type: application/json" \
    -d '{"username": "testuser", "password": "password123"}'
    ```
    This will return a token. Copy the token string.

3.  **Access a protected endpoint:**
    ```bash
    curl http://localhost:8080/api/todo-tasks \
    -H "Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>"
    ```
