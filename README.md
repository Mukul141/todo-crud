-----

# Todo Service API 📝

Welcome to the Todo Service API\! This is a simple yet powerful RESTful API for managing a to-do list, built with Java and the Spring Boot framework. This project serves as a practical demonstration of modern backend development, from local containerization to cloud deployment.

The goal is to build a robust, well-tested, and scalable service following best practices in the software development lifecycle.

-----

## \#\# Current Features ✨

The API currently supports full **CRUD** (Create, Read, Update, Delete) functionality for todo tasks.

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
  * **Database:** PostgreSQL
  * **Data Access:** Spring Data JPA / Hibernate
  * **Build Tool:** Apache Maven
  * **Containerization:** Docker & Docker Compose

-----

## \#\# Getting Started Locally 🚀

You can get the entire application, including the database, up and running on your local machine with a single command\!

### Prerequisites

  * Git
  * Docker and Docker Compose
  * A Java 17 JDK (for code completion in your IDE)

### Quick Start Guide

1.  **Clone the repository:**

    ```bash
    git clone <your-repository-url>
    cd <your-project-directory>
    ```

2.  **Launch the application:**
    Run the following command from the root of the project. This will build the application's Docker image and start both the app and database containers in the background.

    ```bash
    docker-compose up -d --build
    ```

That's it\! The API will be available at `http://localhost:8080`. You can now use a tool like Postman or `curl` to interact with the endpoints listed above.

3.  **To stop the application:**
    ```bash
    docker-compose down
    ```

-----

## \#\# Project Roadmap & Future Goals 🗺️

This project is still evolving\! Here's a look at what's next on the roadmap:

  * [ ] **Validation & Error Handling:** Implement robust input validation and a global exception handler for clean error responses.
  * [ ] **Unit & Integration Testing:** Write comprehensive tests using JUnit 5 and MockMvc to ensure the API is reliable.
  * [ ] **Continuous Integration (CI):** Set up a GitHub Actions workflow to automatically build and test the code on every push to the `main` branch.
  * [ ] **Cloud Deployment:** Deploy the application as a containerized service to **Google Cloud Run**.
  * [ ] **Performance Testing:** Use **k6** to run a load test and analyze latency metrics (p50/p95) in Google Cloud Monitoring.
