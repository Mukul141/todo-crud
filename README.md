

### **The Final Portfolio README**


# Todo Service API: A Cloud-Native & Secure REST API Showcase

[![Java CI with Maven](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml/badge.svg)](https://github.com/Mukul141/todo-crud/actions/workflows/build.yaml)

This project is a comprehensive showcase of a modern, cloud-native, and secure RESTful API built with Java and Spring Boot. It demonstrates a full software development lifecycle, from local containerized development to a fully automated CI/CD pipeline and deployment on the Google Cloud Platform. This repository is not just a demonstration of coding, but of building and operating a reliable, scalable, and secure service.

---

## 🚀 Core Features & Capabilities

This service has been engineered with production-readiness in mind, incorporating several key features that ensure security, scalability, and stability.

* **Secure Authentication & Authorization:** The entire API is protected using **Spring Security** and a stateless **JSON Web Token (JWT)** based authentication system. Endpoints are provided for user registration and login.
* **Full CRUD Functionality:** Provides complete Create, Read, Update, and Delete operations for user-specific to-do tasks.
* **Scalable API Pagination:** The primary `GET` endpoint for listing tasks is paginated, ensuring efficient and fast data retrieval even with large datasets by using `page` and `size` parameters.
* **Request Rate Limiting:** The API is protected from abuse and denial-of-service attacks with a token-bucket based rate limiter (using **Bucket4j**), configured to allow a maximum number of requests per minute from a single client.
* **Robust Input Validation & Error Handling:** User input is validated at the controller level, and a **Global Exception Handler** provides clean, consistent, and user-friendly JSON error responses for all API errors.

---

## 🛠️ Architecture & Tech Stack

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

## ⚙️ DevOps & CI/CD Pipeline

This project is configured with a full Continuous Integration (CI) pipeline using **GitHub Actions**.

1.  **Trigger:** On every `git push` to the `main` branch.
2.  **Job:** A fresh Ubuntu runner is provisioned.
3.  **Build:** The code is checked out, and a full Maven build (`mvn verify`) is executed.
4.  **Test:** The `verify` lifecycle phase automatically runs the entire suite of integration tests against an in-memory H2 database, ensuring that no new code breaks existing functionality.
5.  **Feedback:** The build status (passing/failing) is reported back directly in GitHub, and the status badge on this README is updated.

---

## 💻 Local Development & Usage

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

## 📈 Performance Analysis & Future Work

The final phase of this project involves a detailed performance analysis based on the "OS demo plan."

* [x] **Successful Cloud Deployment:** The secure application is live and connected to a persistent Cloud SQL instance.
* [ ] **Execute k6 Test Matrix:** Run load tests against a 2x2 matrix of Cloud Run configurations (`min-instances`: 0 vs 1, `concurrency`: 1 vs 80).
* [ ] **Analyze & Document:** Capture latency (p50/p95/p99) and cold-start data from Cloud Monitoring and summarize the findings in a `perf.md` file.


````
