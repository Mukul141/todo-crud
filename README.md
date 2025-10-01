# Todo Service API: An "Operating a Service" Showcase

![CI Build Status](https://github.com/Mukul141/todo-crud/actions/workflows/build.yml/badge.svg)

## 1. Project Vision 🚀

This project documents the evolution of a standard Spring Boot CRUD application into a production-ready, observable, and secure RESTful service. The primary goal is to demonstrate best practices in **operating a cloud-native application** on Google Cloud Platform, focusing on security, performance analysis, and reliability.

This repository serves as a practical showcase of the skills required to not just *build* an application, but to *run and maintain* it effectively in a modern cloud environment.

-----

## 2. Current Status: Deployed & Operational ✅

The initial version of this API is complete and has been successfully deployed to **Google Cloud Run**.

**Live API URL:** `https://todo-service-400624648397.asia-south1.run.app`

The currently deployed service includes the following completed features:
* **Full CRUD Functionality:** All create, read, update, and delete operations are functional.
* **Input Validation:** The API uses a global error handler to provide clean responses for invalid requests.
* **Containerization:** The application is fully containerized using Docker and can be run locally via Docker Compose.
* **Continuous Integration (CI):** A GitHub Actions workflow automatically builds and tests the application on every push.
* **Integration & Performance Testing:** The project includes a suite of MockMvc tests for API validation and a k6 script for load testing.

-----

## 3. The Next Phase: Project Evolution Roadmap 🗺️

The next phase of this project is to enhance the existing service with production-grade features and conduct a rigorous performance analysis.

### Phase 1: Service Hardening
- [ ] **Integrate with Cloud SQL:** Replace the in-memory H2 database with a fully-managed PostgreSQL instance on Google Cloud SQL.
- [ ] **Implement API Pagination:** Refactor the `GET /api/todo-tasks` endpoint to support paged data retrieval.
- [ ] **Secure Endpoints with JWT:** Implement user registration and login, and protect all service endpoints using Spring Security.
- [ ] **Add Rate Limiting:** Introduce a basic rate-limiting mechanism to the API.

### Phase 2: Performance Analysis & Reporting
- [ ] **Create `perf.md` Report:** Establish a markdown file to document the performance testing methodology and results.
- [ ] **Execute k6 Test Matrix:** Run load tests against a 2x2 matrix of Cloud Run configurations (min-instances: 0 vs 1, concurrency: 1 vs 80).
- [ ] **Analyze & Document:** Capture latency (p50/p95/p99) and cold-start data from Cloud Monitoring, and summarize the findings in `perf.md`.

-----

## 4. Tech Stack (Current & Planned) 🛠️

* **Backend:** Java 17, Spring Boot 3, Spring Security (JWT)
* **Database:** PostgreSQL, Google Cloud SQL, H2
* **Testing:** JUnit 5, MockMvc, Testcontainers
* **CI/CD:** GitHub Actions, Google Cloud Build, Google Artifact Registry
* **Cloud Platform:** Google Cloud Run, Google Cloud Monitoring
* **Performance Testing:** k6

-----

## 5. Local Development

The project remains fully runnable and testable on a local machine.

* **Run the full stack locally:** `docker-compose up -d --build`
* **Run all tests:** `mvn verify`
