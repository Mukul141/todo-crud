# Todo Service API 📝

Welcome to the Todo Service API\! This is a simple yet powerful RESTful API for managing a to-do list, built with Java and the Spring Boot framework. This project serves as a practical demonstration of modern backend development, from local containerization to cloud deployment.

The goal is to build a robust, well-tested, and scalable service following best practices in the software development lifecycle.

-----

## \#\# Current Features ✨

The API currently supports full **CRUD** (Create, Read, Update, Delete) functionality for todo tasks, complete with input validation and standardized error handling.

  * `GET /api/todo-tasks` - Fetches all tasks.
  * `GET /api/todo-tasks/{id}` - Fetches a single task by its ID.
  * `POST /api/todo-tasks` - Creates a new task.
  * `PUT /api/todo-tasks/{id}` - Updates an existing task.
  * `DELETE /api/todo-tasks/{id}` - Deletes a specific task by its ID.
  * `DELETE /api/todo-tasks` - Deletes all tasks.

-----

## \#\# Tech Stack 🛠️

This project is built using a modern and widely-used set of technologies:

  * **Backend:** Java 17, Spring Boot 3
  * **Database:** PostgreSQL (for local), H2 (for cloud/testing)
  * **Data Access:** Spring Data JPA / Hibernate
  * **Build Tool:** Apache Maven
  * **Containerization:** Docker & Docker Compose
  * **Testing:** JUnit 5 & MockMvc
  * **Cloud & Deployment:** Google Cloud Run, Google Cloud Build, Artifact Registry
  * **Performance Testing:** k6

-----

## \#\# Getting Started Locally 🚀

You can get the entire application, including the database, up and running on your local machine with a single command\!

### Prerequisites

  * Git
  * Docker and Docker Compose
  * A Java 17 JDK

### Quick Start Guide

1.  **Clone the repository:**

    ```bash
    git clone <your-repository-url>
    cd <your-project-directory>
    ```

2.  **Launch the application:**

    ```bash
    docker-compose up -d --build
    ```

    The API will be available at `http://localhost:8080`.

3.  **To stop the application:**

    ```bash
    docker-compose down
    ```

-----

## \#\# Testing ✅

This project uses JUnit 5 and MockMvc for robust integration testing of the API controllers. You can run all tests locally:

```bash
mvn verify
```

Tests are also automatically executed by the **GitHub Actions CI pipeline** on every push to the `main` branch.

-----

## \#\# Cloud Deployment ☁️

This application has been successfully deployed to the cloud using **Google Cloud Run**, making it a publicly accessible serverless API.

**Live API URL:** `https://todo-service-400624648397.asia-south1.run.app`

The deployment pipeline uses Google Cloud Build to automatically build the Docker image from the `Dockerfile`, store it in Artifact Registry, and deploy the new version to Cloud Run.

-----

## \#\# Project Complete ✅

All initial goals for this project have been met, covering the full development lifecycle.

  * [x] **Validation & Error Handling:** Implemented robust input validation and a global exception handler.
  * [x] **Unit & Integration Testing:** Wrote comprehensive tests for happy and unhappy paths.
  * [x] **Continuous Integration (CI):** Set up a GitHub Actions workflow to automatically build and test the code.
  * [x] **Cloud Deployment:** Deployed the application as a containerized service to **Google Cloud Run**.
  * [x] **Performance Testing:** Used **k6** to run a load test and analyze latency metrics in Google Cloud Monitoring.
