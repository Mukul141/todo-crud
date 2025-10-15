# Todo Service API: A Cloud-Native & Secure REST API Showcase

[![Java CI with Maven](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml/badge.svg)](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml)

## 1. Project Vision 🚀

This project is a comprehensive showcase of a modern, cloud-native, and secure RESTful API built with Java and Spring Boot. It demonstrates a full software development lifecycle, from local containerized development to a fully automated CI/CD pipeline and deployment on the Google Cloud Platform. This repository is not just a demonstration of coding, but of building and operating a reliable, scalable, and secure service.

---

## 2. Core Features & Capabilities ✅

This service has been engineered with production-readiness in mind, incorporating several key features that ensure security, scalability, and stability.

* **Secure Authentication & Authorization:** The entire API is protected using **Spring Security** and a stateless **JSON Web Token (JWT)** based authentication system. Public endpoints are provided for user registration and login.
* **Full CRUD Functionality:** Provides complete Create, Read, Update, and Delete operations for user tasks.
* **Scalable API Pagination:** The primary `GET` endpoint for listing tasks is paginated, ensuring efficient and fast data retrieval even with large datasets by using `page` and `size` parameters.
* **Request Rate Limiting:** The API is protected from abuse and denial-of-service attacks with a token-bucket based rate limiter (using **Bucket4j**), configured to reject excessive traffic.
* **Robust Input Validation & Error Handling:** User input is validated at the controller level, and a **Global Exception Handler** provides clean, consistent, and user-friendly JSON error responses for all API errors.

---

## 3. Architecture & Tech Stack 🛠️

This project utilizes a modern, cloud-native architecture. The local development environment mirrors the cloud deployment, ensuring consistency across all stages.

### High-Level Architecture

```

\+-----------+      +-------------------+      +-----------------+
|   User    |-----\>| Google Cloud Run  |-----\>| Google Cloud SQL|
| (Client)  |      | (Spring Boot App) |      |   (PostgreSQL)  |
\+-----------+      +-------------------+      +-----------------+
^                      ^
|                      |
\+-----------+      +-------------------+      +--------------------+
| Developer |-----\>|      GitHub       |-----\>|  Google Cloud Build|
\+-----------+      | (Git Repository)  |      |  (CI/CD Pipeline)  |
\+-------------------+      +---------+----------+
|
v
\+--------------------+
| Artifact Registry  |
|  (Docker Images)   |
\+--------------------+

````

### Technology Choices

| Category | Technology | Rationale & Skills Demonstrated |
| :--- | :--- | :--- |
| **Backend** | Java 17, Spring Boot 3 | Modern Java development, dependency injection, building RESTful APIs, configuration management (profiles). |
| **Database** | PostgreSQL, Google Cloud SQL, H2 | Experience with relational databases, managed cloud SQL, and in-memory DBs for testing. |
| **Security** | Spring Security, JWT (jjwt library) | Implementing stateless, token-based authentication and authorization from scratch. Password hashing with BCrypt. |
| **API Stability**| Bucket4j | Implementing rate limiting to protect services from abuse and ensure high availability. |
| **Testing** | JUnit 5, MockMvc, Spring Security Test | Writing robust integration tests for a secure, multi-layered application. Understanding of test environments. |
| **DevOps** | Docker, Docker Compose, GitHub Actions | Containerization, multi-stage Docker builds, local environment orchestration, and building automated CI pipelines. |
| **Cloud** | Google Cloud Run, Cloud Build, Artifact Registry | Deploying and operating a serverless, containerized application on a major cloud platform. |
| **Performance**| k6 | Scripting and executing load tests to analyze application performance under stress. |

---

## 4. DevOps & CI/CD Pipeline ⚙️

This project is configured with a full Continuous Integration (CI) pipeline using **GitHub Actions**.

1.  **Trigger:** On every `git push` to the `main` branch.
2.  **Job:** A fresh Ubuntu runner is provisioned.
3.  **Build:** The code is checked out, and a full Maven build (`mvn verify`) is executed.
4.  **Test:** The `verify` lifecycle phase automatically runs the entire suite of integration tests against an in-memory H2 database, ensuring that no new code breaks existing functionality.
5.  **Feedback:** The build status (passing/failing) is reported back directly in GitHub, and the status badge on this README is updated.

---

## 5. Local Development & Usage 💻

The project is designed for easy local setup using Docker.

* **Run the full stack locally:** `docker-compose up -d --build`
* **Run all tests locally:** `mvn verify`

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
    (This will return a token. Copy the token string.)

3.  **Access a protected endpoint:**
    ```bash
    curl http://localhost:8080/api/todo-tasks \
    -H "Authorization: Bearer <PASTE_YOUR_TOKEN_HERE>"
    ```
---

## 6. Performance Test Results 📈

A load test was performed against the live, deployed service on Google Cloud Run to validate performance and stability under load.

* **Tool:** k6
* **Load Profile:** Ramped up to 10 virtual users over a 30-second period.
* **Key Findings:**
    * **High Performance:** The service demonstrated excellent performance, with a **p95 latency of ~45ms**, well below the 500ms target. This proves the application is fast and responsive.
    * **Rate Limiting Success:** The test successfully triggered the `429 Too Many Requests` error, proving that the **rate-limiting feature works correctly** under load to protect the service from excessive traffic.
    * **Cold Start Observation:** Analysis in Cloud Monitoring showed a clear "cold start" latency spike on the first request, followed by a rapid drop to a stable, low latency for all subsequent "warm" requests. This is the expected and desired behavior of a serverless application.

---

## 7. Potential Future Enhancements 🔮

While this project is complete, several advanced features could be added to further enhance it:

* **User-Specific Data:** Refactor the service layer to ensure users can only access and modify their own `todo-tasks`.
* **Continuous Deployment (CD):** Enhance the GitHub Actions workflow to automatically deploy the application to Cloud Run *if and only if* all tests pass.
* **Advanced Testing with Testcontainers:** Replace the H2 database in the test environment with Testcontainers to run integration tests against a real, temporary PostgreSQL container.
* **Externalized Secret Management:** Integrate with Google Secret Manager to handle the database password, which is the gold standard for security in the cloud.

